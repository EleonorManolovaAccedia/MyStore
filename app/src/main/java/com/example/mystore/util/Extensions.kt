package com.example.mystore.util

fun String.isOverSevenCharacters() = length >= 7
fun String.hasDigits() = count(Char::isDigit) > 0