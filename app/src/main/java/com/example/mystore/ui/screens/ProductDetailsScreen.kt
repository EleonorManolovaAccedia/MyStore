package com.example.mystore.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mystore.viewmodels.ProductDetailsViewModel


@Composable
fun ProductDetailsScreen() {
    var productViewModel: ProductDetailsViewModel = hiltViewModel()
    var product = productViewModel.getProduct()
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {}, enabled = false) {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(5.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Item",
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                textAlign = TextAlign.Center
            )
            IconButton(onClick = {}, enabled = false) {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(5.dp),
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Shopping cart"
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.labelLarge.fontSize
                )
                RatingBar(
                    rating = product.rating,
                    stars = 5,
                    starsColor = Color.Magenta
                )
            }

            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "Category: ${product.category}",
                color = Color.Gray,
                fontSize = MaterialTheme.typography.labelMedium.fontSize
            )
            Text(modifier = Modifier.padding(bottom = 20.dp), text = product.description)
            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "$${product.price}",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                onClick = {},
                enabled = false
            ) {
                Icon(imageVector = Icons.Filled.Add, "Add to cart")
                Text(text = "Add to cart")
            }

        }
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    stars: Int = 5,
    starsColor: Color = Color.Magenta,
) {
    val unfilledStars = stars - rating
    Row(modifier = modifier) {
        Text(modifier = Modifier.padding(end = 4.dp), text = rating.toString())
        repeat(rating) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}