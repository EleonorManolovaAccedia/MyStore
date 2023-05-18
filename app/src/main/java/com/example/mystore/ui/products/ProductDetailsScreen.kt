package com.example.mystore.ui.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.mystore.ui.theme.Green40
import com.example.mystore.ui.theme.Purple40
import com.example.mystore.ui.theme.Red40
import kotlin.math.ceil


@Composable
fun ProductDetailsScreen() {
    val productViewModel: ProductDetailsViewModel = hiltViewModel()
    productViewModel.getProduct(productId = 1)
    productViewModel.product?.let {
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
                    fontSize = 24.sp,
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
                Box {
                    Image(
                        painter = rememberAsyncImagePainter(it.image),
                        contentDescription = "product image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(300.dp)
                            .clip(RoundedCornerShape(16.dp))

                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 10.dp
                            ),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                if (it.rating.count > 0) {
                                    Icon(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .padding(5.dp),
                                        tint = Green40,
                                        imageVector = Icons.Outlined.CheckCircle,
                                        contentDescription = "In Stock"
                                    )
                                    Text(text = "In stock")
                                } else {
                                    Icon(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .padding(5.dp),
                                        tint = Red40,
                                        imageVector = Icons.Outlined.Cancel,
                                        contentDescription = "Stock"
                                    )
                                    Text(text = "Out of stock", color = Red40)
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = it.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.labelLarge.fontSize,
                        modifier = Modifier.width(200.dp)
                    )
                    RatingBar(
                        rating = it.rating.rate,
                        stars = 5
                    )
                }
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = "Category: ${it.category}",
                    color = Color.Gray,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize
                )
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = it.description
                )
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = "$${it.price}",
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
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float = 0.0f,
    stars: Int = 5,
    starsColor: Color = Purple40,
) {
    val unfilledStars = stars - ceil(rating).toInt()
    Row(modifier = modifier) {
        Text(modifier = Modifier.padding(end = 4.dp), text = ceil(rating).toInt().toString())
        repeat(ceil(rating).toInt()) {
            Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = starsColor)
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = Color.Gray.copy(alpha = 0.2f)
            )
        }
    }
}