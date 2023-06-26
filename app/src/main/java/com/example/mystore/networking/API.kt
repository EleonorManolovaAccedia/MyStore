package com.example.mystore.networking

import com.example.mystore.model.LoginModel
import com.example.mystore.model.LoginResponse
import com.example.mystore.model.ProductModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface API {
    @POST(value = "auth/local")
    suspend fun login(@Body loginModel: LoginModel): LoginResponse

    @GET(value = "products/{productId}?populate=*")
    suspend fun getProduct(@Path("productId") productId: Int): ProductModel

    @GET(value = "products?populate=*")
    suspend fun getProducts(): List<ProductModel>
}