package com.example.mystore.repository

import com.example.mystore.model.ProductModel

interface ProductsRepository {
    suspend fun getProduct(productId: Int): ProductModel
}