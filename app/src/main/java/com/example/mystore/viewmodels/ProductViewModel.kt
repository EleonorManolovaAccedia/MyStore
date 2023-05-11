package com.example.mystore.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mystore.ProductsRepository
import com.example.mystore.models.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {
    var name by mutableStateOf("")
    var description by mutableStateOf("")
    var category by mutableStateOf("")
    var price by mutableStateOf(0.0f)
    var rating by mutableStateOf(0)
    private val productModel: ProductModel
        get() = ProductModel(
            name = "Stargizer's Tea Set",
            description = "Include in our heavenly tea experience with our Stargizer's Tea Set, featuring a constellation-themed teapot and two matching teacups. Crafted from fine porcelain, this elegant set will transport you to the cosmos with every sip.",
            category = "Home",
            price = 90.00f,
            rating = 5
        )

    fun getProduct(): ProductModel {
        return productModel
    }
}