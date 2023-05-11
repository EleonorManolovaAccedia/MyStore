package com.example.mystore

import com.example.mystore.networking.API

class ProductsRepository(private val api: API) : ApiHelper {
   override suspend fun getProduct() = api.getProduct()
}