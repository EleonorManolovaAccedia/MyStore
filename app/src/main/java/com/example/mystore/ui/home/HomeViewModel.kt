package com.example.mystore.ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.model.CategoryDetailsModel
import com.example.mystore.model.FiltersModel
import com.example.mystore.model.ProductModel
import com.example.mystore.repository.IApiRepository
import com.example.mystore.repository.IDataStoreRepository
import com.example.mystore.util.containsString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiRepository: IApiRepository,
    private val dataStoreRepository: IDataStoreRepository
) : ViewModel() {
    private var allProducts = MutableStateFlow(emptyList<ProductModel>())
    var products = MutableStateFlow(emptyList<ProductModel>())
    var isLoading = MutableStateFlow(false)
    var categories = MutableStateFlow(emptyList<CategoryDetailsModel>())
    var input = MutableStateFlow("")

    private var priceStartRange by mutableFloatStateOf(20f)
    private var priceEndRange by mutableFloatStateOf(150f)
    private var rating by mutableIntStateOf(4)
    private var selectedCategories = mutableStateListOf<String>().apply {
        addAll(listOf("Home"))
    }
    var filtersCount by mutableIntStateOf(3)
    var filtersModel = FiltersModel(priceStartRange, priceEndRange, rating, selectedCategories)
    val shoppingCartCount
        get() = dataStoreRepository.getShoppingCart().count()

    init {
        getProducts()
        getCategories()
    }

    fun filterProducts(newInput: String? = null) {
        products.update { emptyList() }
        products.update { allProducts.value.filter { it.price in (priceStartRange..priceEndRange) && it.rating <= rating } }

        if (selectedCategories.isNotEmpty() && selectedCategories.count() != categories.value.count()) {
            products.update { products -> products.filter { selectedCategories.contains(it.category) } }
        }
        newInput?.let {
            input.value = it
        }

        if (input.value.isNotEmpty()) {
            products.update {
                it.filter { product ->
                    product.title.containsString(input.value) ||
                            product.short_description.containsString(input.value) ||
                            product.category.containsString(input.value)
                }
            }
        }
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
        input.value = ""
        filterProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            isLoading.value = true

            try {
                allProducts.update { emptyList() }
                allProducts.value = apiRepository.getProducts()
                filterProducts()
            } catch (e: Exception) {
                println(e)
            }
            isLoading.value = false
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            try {
                categories.update { emptyList() }
                categories.update { apiRepository.getCategories() }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}