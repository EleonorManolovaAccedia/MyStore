package com.example.mystore.repository

import com.example.mystore.model.CategoryDetailsModel
import com.example.mystore.model.LoginModel
import com.example.mystore.model.LoginResponse
import com.example.mystore.model.ProductModel

interface IApiRepository  {

    suspend fun login(loginModel: LoginModel): LoginResponse

    suspend fun getProduct(productId: Int): ProductModel

    suspend fun getProducts(): List<ProductModel>

    suspend fun getCategories(): List<CategoryDetailsModel>
}