package com.example.mystore.repository

import com.example.mystore.model.CategoryDetailsModel
import com.example.mystore.model.LoginModel
import com.example.mystore.model.LoginResponse
import com.example.mystore.model.ProductModel
import com.example.mystore.networking.API
import javax.inject.Inject

class ApiRepository @Inject constructor(private val api: API) : IApiRepository {

    override suspend fun login(loginModel: LoginModel): LoginResponse {
        return api.login(loginModel)
    }

    override suspend fun getProduct(productId: Int): ProductModel {
        return api.getProduct(productId)
    }

    override suspend fun getProducts(): List<ProductModel> {
        return api.getProducts()
    }

    override suspend fun getCategories(): List<CategoryDetailsModel> {
        return api.getCategories()
    }
}