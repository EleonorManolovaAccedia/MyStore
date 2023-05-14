package com.example.mystore.networking

import com.example.mystore.models.ProductModel
import retrofit2.http.GET
interface API {
    @GET(value = "/api")
    suspend fun getProduct() : ProductModel
}