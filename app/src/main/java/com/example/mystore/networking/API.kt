package com.example.mystore.networking

import com.example.mystore.model.LoginModel
import com.example.mystore.model.LoginResponse
import com.example.mystore.model.ProductModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface API {
    @POST(value = "auth/local")
    @Headers("No-Authentication: true")
    suspend fun login(@Body loginModel: LoginModel ) : Response<LoginResponse>

    @GET("products/{productId}?populate=*")
    suspend fun getProduct(@Path("productId") productId: Int): Response<ProductModel>
}