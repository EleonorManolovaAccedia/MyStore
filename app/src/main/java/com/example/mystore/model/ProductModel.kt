package com.example.mystore.model

data class Products(val products: List<ProductModel>)

data class ProductModel(
    val title: String,
    val description: String,
    val category: String,
    val price: Float,
    val rating: Rating,
    val image: String
)

data class Rating(val rate: Float, val count: Int)