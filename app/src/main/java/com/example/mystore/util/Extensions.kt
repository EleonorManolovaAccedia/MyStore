package com.example.mystore.util

import android.util.Patterns
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.isOverSevenCharacters() = length >= 7
fun String.hasDigits() = count(Char::isDigit) > 0
fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()
fun String.containsString(string: String) = this.lowercase().contains(string.lowercase())
fun Double.convertToUsCurrency() = (NumberFormat.getCurrencyInstance(Locale.US).format(this))
fun Date.parse(pattern: String): String = SimpleDateFormat(pattern, Locale.getDefault()).format(this)
