package com.example.mystore.ui.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.repository.ProductsRepositoryImpl
import com.example.mystore.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ProductsRepositoryImpl
) : ViewModel() {
    var productModel by mutableStateOf<ProductModel?>(null)

    fun getProduct(productId: Int) {
        viewModelScope.launch {
            try {
                productModel = repository.getProduct(productId)
            } catch (e: Exception) {
                println("Exception: $e")
            }
        }
    }
}