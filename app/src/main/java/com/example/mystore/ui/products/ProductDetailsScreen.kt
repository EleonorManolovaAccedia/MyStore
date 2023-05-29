package com.example.mystore.ui.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.mystore.R
import com.example.mystore.ui.shared.CustomTopBar
import com.example.mystore.ui.shared.RatingBar
import com.example.mystore.ui.theme.DarkPurple
import com.example.mystore.ui.theme.Gray
import com.example.mystore.ui.theme.Green40
import com.example.mystore.ui.theme.LightGray20
import com.example.mystore.ui.theme.LightGray40
import com.example.mystore.ui.theme.Red
import com.example.mystore.ui.theme.White
import com.example.mystore.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen() {
    val productViewModel: ProductDetailsViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        productViewModel.getProduct(productId = 1)
    }
    productViewModel.productModel?.let {
        Scaffold(topBar = {
            CustomTopBar(title = stringResource(id = R.string.products_details_page_title))
        }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Box {
                    Image(
                        painter = rememberAsyncImagePainter(it.image),
                        contentDescription = "Product image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(dimensionResource(id = R.dimen.image_size))
                            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_medium)))
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.padding_medium_20)),
                            colors = CardDefaults.cardColors(containerColor = White),
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_large)),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = dimensionResource(id = R.dimen.elevation)
                            ),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                if (it.stock > 0) {
                                    Icon(
                                        modifier = Modifier
                                            .size(dimensionResource(id = R.dimen.icon_size))
                                            .padding(dimensionResource(id = R.dimen.padding_extra_small)),
                                        tint = Green40,
                                        imageVector = Icons.Outlined.CheckCircle,
                                        contentDescription = stringResource(id = R.string.in_stock_label)
                                    )
                                    Text(text = stringResource(id = R.string.in_stock_label))
                                } else {
                                    Icon(
                                        modifier = Modifier
                                            .size(dimensionResource(id = R.dimen.icon_size))
                                            .padding(dimensionResource(id = R.dimen.padding_extra_small)),
                                        tint = Red,
                                        imageVector = Icons.Outlined.Cancel,
                                        contentDescription = stringResource(id = R.string.out_of_stock_label),
                                    )
                                    Text(
                                        text = stringResource(id = R.string.out_of_stock_label),
                                        color = Red
                                    )
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.padding_top_medium_20)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = it.title,
                        modifier = Modifier.width(dimensionResource(id = R.dimen.width_200)),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    RatingBar(
                        rating = it.rating,
                        stars = Constants.MAX_RATING
                    )
                }
                Text(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_bottom_medium)),
                    text = "Category: ${it.category}",
                    color = Gray,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_bottom_medium)),
                    text = it.description,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_bottom_medium)),
                    text = "$${it.price}",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                    onClick = {},
                    enabled = it.stock > 0,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkPurple,
                        contentColor = White,
                        disabledContainerColor = LightGray20,
                        disabledContentColor = LightGray40
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = dimensionResource(id = R.dimen.elevation),
                        disabledElevation = dimensionResource(id = R.dimen.elevation)
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        stringResource(id = R.string.add_to_cart_label)
                    )
                    Text(text = stringResource(id = R.string.add_to_cart_label))
                }
            }
        }
    }
}