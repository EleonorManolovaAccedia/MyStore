package com.example.mystore

class TokenManager(private var jwtToken: String? = null) {

    fun saveToken(token: String?) {
        jwtToken = token
    }

    fun getToken(): String? {
        return jwtToken
    }

    fun clearToken() {
        jwtToken = null
    }
}