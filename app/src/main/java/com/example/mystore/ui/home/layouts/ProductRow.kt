package com.example.mystore.ui.home.layouts


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.example.mystore.R
import com.example.mystore.model.ProductModel
import com.example.mystore.ui.products.layouts.RatingBar
import com.example.mystore.ui.theme.Gray
import com.example.mystore.ui.theme.LightGray40
import com.example.mystore.ui.theme.Black
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductRow(
    product: ProductModel,
    onclick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .clickable { onclick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = "Product image",
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.product_image_size))
                    .width(dimensionResource(id = R.dimen.product_image_size))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_medium)))
            )

            Column(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium_horizontal))) {
                Text(
                    text = "Category: ${product.category}",
                    color = Gray,
                    style = MaterialTheme.typography.labelSmall,
                )

                Text(
                    text = product.title,
                    color = Black,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )

                Text(
                    text = product.short_description,
                    color = Gray,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_small))
                )

                RatingBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end= dimensionResource(id = R.dimen.padding_large)),
                    rating = product.rating
                )

                Text(
                    text = NumberFormat.getCurrencyInstance(Locale("en", "US"))
                        .format(product.price),
                    color = Black,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_small))
                )
            }
        }

        Divider(
            color = LightGray40,
            thickness = dimensionResource(id = R.dimen.thickness_small),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.padding_small_15))
                .padding(bottom = dimensionResource(id = R.dimen.padding_small_15))
        )
    }
}