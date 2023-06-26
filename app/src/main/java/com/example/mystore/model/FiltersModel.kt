package com.example.mystore.model

data class FiltersModel(
    var priceStart: Float,
    var priceEnd: Float,
    var rating: Int,
    var selectedCategories: List<String>
)