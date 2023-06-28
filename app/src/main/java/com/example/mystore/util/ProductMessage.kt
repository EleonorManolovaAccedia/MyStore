package com.example.mystore.util

enum class ProductMessage(val messages: String) {
    SUCCESS_MESSAGE_ADD_TO_CART( "Successfully added to the cart."),
    ERROR_MESSAGE_OUT_OF_STOCK( "Item was out of stock."),
    ERROR_MESSAGE( "There was a problem.")
}