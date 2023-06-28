package com.example.mystore.repository

import com.example.mystore.model.OrderModel
import com.example.mystore.model.ShoppingCartModel

interface IDataStoreRepository {
    fun getShoppingCart(): List<ShoppingCartModel>

    fun saveShoppingCart(data: List<ShoppingCartModel>): Boolean

    fun getOrderHistory(): List<OrderModel>

    fun saveOrder(data: OrderModel): Boolean

    suspend fun putItem(key: String, value: String)

    fun getItem(key: String): String?

    fun clearItem(key: String)
}