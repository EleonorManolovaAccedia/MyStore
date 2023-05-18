package com.example.mystore.ui.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.repository.ProductsRepository
import com.example.mystore.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {
    var product: ProductModel? by mutableStateOf(null)
    var productResponse: Response<ProductModel>? by mutableStateOf(null)

    fun getProduct(productId: Int) {
        viewModelScope.launch {
            productResponse = repository.getProduct(productId)
            withContext(Dispatchers.IO) {
                try {
                    val localResponse = productResponse as Response<ProductModel>
                    if (localResponse.isSuccessful) {
                        localResponse.body()?.let { it ->
                            product = it
                        }
                    } else {
                        println("Not successful call")
                    }
                } catch (e: Exception) {
                    println("Exception: $e")
                }
            }
        }
    }
}