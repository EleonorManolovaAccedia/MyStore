package com.example.mystore

import com.example.mystore.models.ProductModel

interface ApiHelper {
    suspend fun getProduct(): ProductModel
}