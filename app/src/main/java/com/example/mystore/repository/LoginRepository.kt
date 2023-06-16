package com.example.mystore.repository

import com.example.mystore.model.LoginModel
import com.example.mystore.model.LoginResponse

interface LoginRepository {
    suspend fun login(loginModel: LoginModel): LoginResponse
}