package com.example.mystore

import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor(private val tokenManager: TokenManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (request.header("No-Authentication") == null) {
            val token = tokenManager.getToken()
            if (!token.isNullOrEmpty()) {
                val finalToken = "Bearer " + token
                request = request.newBuilder()
                    .addHeader("Authorization", finalToken)
                    .build()
            }
        }

        return chain.proceed(request)
    }
}