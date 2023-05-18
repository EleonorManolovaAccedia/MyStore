package com.example.mystore.repository

import com.example.mystore.model.LoginModel
import com.example.mystore.model.LoginResponse
import retrofit2.Response

interface ILoginRepository {
    suspend fun login(loginModel: LoginModel): Response<LoginResponse>

    suspend fun saveToken(token: String?)
}