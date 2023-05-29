package com.example.mystore.util

import android.util.Patterns

fun String.isOverSevenCharacters() = length >= 7
fun String.hasDigits() = count(Char::isDigit) > 0
fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()