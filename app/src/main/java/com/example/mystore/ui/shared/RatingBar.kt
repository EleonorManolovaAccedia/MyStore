package com.example.mystore.ui.shared

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mystore.ui.theme.LightGray
import com.example.mystore.ui.theme.Purple40
import com.example.mystore.util.Constants.MAX_RATING


@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    stars: Int = MAX_RATING,
    starsColor: Color = Purple40,
) {
    val unfilledStars = stars - rating
    Row(modifier = modifier) {
        Text(modifier = Modifier.padding(end = 4.dp), text = rating.toString())
        repeat(rating) {
            Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = starsColor)
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = LightGray
            )
        }
    }
}