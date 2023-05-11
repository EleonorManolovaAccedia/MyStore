package com.example.mystore.models

data class Products(val products: List<ProductModel>)

data class ProductModel(
    val name: String,
    val description: String,
    val category: String,
    val price: Float,
    val rating: Int
)