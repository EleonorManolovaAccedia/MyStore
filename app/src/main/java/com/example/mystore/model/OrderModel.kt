package com.example.mystore.model

import java.util.Date

data class OrderModel (val orderDate: Date, val items: List<ShoppingCartModel>)