package com.example.mystore.model

data class ShoppingCartModel(
    val productId: Int,
    val title: String,
    val image: String,
    val price: Double,
    var quantity: Int
)