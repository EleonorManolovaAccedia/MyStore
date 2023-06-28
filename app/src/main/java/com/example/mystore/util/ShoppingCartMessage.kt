package com.example.mystore.util

enum class ShoppingCartMessage(val messages: String) {
    ERROR_MESSAGE ("There was a problem."),
    SUCCESS_MESSAGE_CHECKOUT ("Checkout successful."),
    ERROR_MESSAGE_CHECKOUT ("Checkout not successful."),
    SUCCESS_MESSAGE_REMOVE_FROM_CART ("Successfully removed from the cart.")
}