package com.example.mystore.repository

import com.example.mystore.model.ProductModel
import com.example.mystore.networking.API
import retrofit2.Response

class ProductsRepository(private val api: API) : IProductsRepository {
    override suspend fun getProduct(productId: Int): Response<ProductModel> {
        return api.getProduct(productId)
    }
}