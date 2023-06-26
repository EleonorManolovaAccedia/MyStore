package com.example.mystore.ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.model.ProductModel
import com.example.mystore.repository.IApiRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiRepository: IApiRepository
) : ViewModel() {
    var products = mutableStateListOf<ProductModel>()
    var isLoading by mutableStateOf(false)

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            isLoading = true

            try {
                products.apply {
                    clear()
                    addAll(apiRepository.getProducts())
                }
            } catch (e: Exception) {
                println(e)
            }
            isLoading = false
        }
    }

    fun filterProducts(input: String): List<ProductModel> {
        return if (input.isNotEmpty()) products.filter {
            it.title.lowercase().contains(input.lowercase()) || it.short_description.lowercase()
                .contains(input.lowercase()) || it.category.lowercase().contains(input.lowercase())
        } else products
    }
}