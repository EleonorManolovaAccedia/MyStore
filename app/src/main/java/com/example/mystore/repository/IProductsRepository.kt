package com.example.mystore.repository

import com.example.mystore.model.ProductModel
import retrofit2.Response

interface IProductsRepository {
    suspend fun getProduct(productId: Int): Response<ProductModel>
}