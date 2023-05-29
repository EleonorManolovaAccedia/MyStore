package com.example.mystore.model

data class Products(val products: List<ProductModel>)

data class ProductModel(
    val id: Int,
    val title: String,
    val description: String,
    val short_description: String,
    val category: String,
    val price: Float,
    val rating: Int,
    val image: String,
    val stock: Int,
)
