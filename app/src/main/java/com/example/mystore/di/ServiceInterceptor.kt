package com.example.mystore.di

import com.example.mystore.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ServiceInterceptor(private val tokenManager: TokenManager) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val token = tokenManager.getToken()

        println("Token: $token")
        if (request.url.encodedPath != "/api/auth/local" && token != null) {
            val authenticatedRequest = request.newBuilder()
                .header("Authorization", "Bearer $token").build()
            return chain.proceed(authenticatedRequest)
        } else {
            tokenManager.clearToken()
        }

        return chain.proceed(request)
    }
}