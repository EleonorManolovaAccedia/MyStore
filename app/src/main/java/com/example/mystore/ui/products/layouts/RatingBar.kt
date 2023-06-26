package com.example.mystore.ui.products.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    onChange: (Int) -> Unit = {},
    canChange: Boolean = false
) {
    val unfilledStars = stars - rating
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        if (!canChange)
            Text(modifier = Modifier.padding(end = 4.dp), text = rating.toString())
        repeat(rating) {
            if (canChange) {
                IconButton(onClick = { onChange(it + 1) }) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = starsColor
                    )
                }
            } else
                Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = starsColor)
        }
        repeat(unfilledStars) {
            if (canChange) {
                IconButton(onClick = { onChange(rating + it + 1) }) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = LightGray
                    )
                }
            } else
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    tint = LightGray
                )
        }
    }
}