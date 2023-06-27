package com.example.mystore.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductModel(
    val id: Int,
    val title: String,
    val description: String,
    val short_description: String,
    val category: String,
    val price: Float,
    val rating: Int,
    val image: String,
    val stock: Int,
): Parcelable
