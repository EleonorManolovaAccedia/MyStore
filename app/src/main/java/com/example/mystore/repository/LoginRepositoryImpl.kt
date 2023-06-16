package com.example.mystore.repository

import com.example.mystore.model.LoginModel
import com.example.mystore.model.LoginResponse
import com.example.mystore.networking.API

class LoginRepositoryImpl(
    private val api: API
) : LoginRepository {
    override suspend fun login(loginModel: LoginModel): LoginResponse {
        return api.login(loginModel)
    }
}