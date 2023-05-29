package com.example.mystore.repository

import com.example.mystore.model.ProductModel
import com.example.mystore.networking.API

class ProductsRepositoryImpl(private val api: API) : ProductsRepository {
    override suspend fun getProduct(productId: Int): ProductModel {
        return api.getProduct(productId)
    }
}