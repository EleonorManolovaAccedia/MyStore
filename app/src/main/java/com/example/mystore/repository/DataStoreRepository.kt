package com.example.mystore.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mystore.model.OrderModel
import com.example.mystore.model.ShoppingCartModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DataStore")

class DataStoreRepository(
    private val context: Context,
    private val gson: Gson
) : IDataStoreRepository {

    override fun getShoppingCart(): List<ShoppingCartModel> {
        val jsonData = getItem("cart") ?: "[]"
        return gson.fromJson(jsonData, object : TypeToken<List<ShoppingCartModel>>() {}.type)
    }

    override fun saveShoppingCart(data: List<ShoppingCartModel>): Boolean {
        val jsonData = gson.toJson(data, object : TypeToken<List<ShoppingCartModel>>() {}.type)
        runBlocking { putItem("cart", jsonData) }
        return true
    }

    override fun getOrderHistory(): List<OrderModel> {
        val jsonData = getItem("orders") ?: "[]"
        return gson.fromJson(jsonData, object : TypeToken<List<OrderModel>>() {}.type)
    }

    override fun saveOrder(data: OrderModel): Boolean {

        val orderHistory = getOrderHistory().toMutableList()
        orderHistory.add(data)

        val jsonData = gson.toJson(orderHistory, object : TypeToken<List<OrderModel>>() {}.type)
        runBlocking { putItem("orders", jsonData) }
        return true
    }

    override suspend fun putItem(key: String, value: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    override fun getItem(key: String): String? {
        return try {
            val preferences = runBlocking { context.dataStore.data.first() }
            preferences[stringPreferencesKey(key)]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun clearItem(key: String) {
        runBlocking {
            context.dataStore.edit { preferences ->
                preferences.remove(stringPreferencesKey(key))
            }
        }
    }
}


