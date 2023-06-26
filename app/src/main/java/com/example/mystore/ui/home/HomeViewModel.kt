package com.example.mystore.ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.model.CategoryDetailsModel
import com.example.mystore.model.FiltersModel
import com.example.mystore.model.ProductModel
import com.example.mystore.repository.IApiRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiRepository: IApiRepository
) : ViewModel() {
    private var allProducts = mutableStateListOf<ProductModel>()
    var products = mutableStateListOf<ProductModel>()
    var isLoading by mutableStateOf(false)
    var categories = mutableStateListOf<CategoryDetailsModel>()

    var input by mutableStateOf("")
    private var priceStartRange by mutableFloatStateOf(20f)
    private var priceEndRange by mutableFloatStateOf(150f)
    private var rating by mutableIntStateOf(4)
    private var selectedCategories = mutableStateListOf<String>().apply {
        addAll(listOf("Home"))
    }
    var filtersCount by mutableIntStateOf(3)

    init {
        getProducts()
        getCategories()
    }

    fun filterProducts(newInput: String? = null) {
        products.apply {
            clear()
            addAll(allProducts.filter {
                it.price in (priceStartRange..priceEndRange) && it.rating <= rating
            })
        }

        if (selectedCategories.isNotEmpty() && selectedCategories.count() != categories.count()) {
            products.apply {
                removeAll(products.filter {
                    !selectedCategories.contains(it.category)
                })
            }
        }

        if (newInput != null) {
            input = newInput
        }
        if (input.isNotEmpty()) {
            products.apply {
                removeAll(
                    products.filter { product ->
                        !(product.title.lowercase().contains(input.lowercase()) ||
                                product.short_description.lowercase()
                                    .contains(input.lowercase()) ||
                                product.category.lowercase().contains(input.lowercase()))
                    }
                )
            }
        }
    }

    fun getFilters(): FiltersModel {
        return FiltersModel(priceStartRange, priceEndRange, rating, selectedCategories)
    }

    fun setFilters(filters: FiltersModel) {
        priceStartRange = filters.priceStart
        priceEndRange = filters.priceEnd
        rating = filters.rating
        selectedCategories.apply {
            clear()
            addAll(filters.selectedCategories)
        }

        filterProducts()
    }

    fun clearInput() {
        input = ""
        filterProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            isLoading = true

            try {
                allProducts.apply {
                    clear()
                    addAll(apiRepository.getProducts())
                }
                filterProducts()
            } catch (e: Exception) {
                println(e)
            }
            isLoading = false
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            try {
                categories.apply {
                    clear()
                    addAll(apiRepository.getCategories())
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}