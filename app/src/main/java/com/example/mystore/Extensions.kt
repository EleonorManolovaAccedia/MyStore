package com.example.mystore

fun String.isOverEightCharacters() = length >= 8
fun String.hasDigits() = count(Char::isDigit) > 0
fun String.isMixedCase() = any(Char::isLowerCase) && any(Char::isUpperCase)