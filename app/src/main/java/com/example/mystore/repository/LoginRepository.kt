package com.example.mystore.repository

import com.example.mystore.TokenManager
import com.example.mystore.model.LoginModel
import com.example.mystore.model.LoginResponse
import com.example.mystore.networking.API
import com.example.mystore.networking.ApiClient
import retrofit2.Response

class LoginRepository(
    private val api: API,
    private val apiClient: ApiClient,
    private val tokenManager: TokenManager
) : ILoginRepository {
    override suspend fun login(loginModel: LoginModel): Response<LoginResponse> {
        return apiClient.defaultService.login(loginModel)
    }

    override suspend fun saveToken(token: String?) {
        tokenManager.saveToken(token)
    }
}