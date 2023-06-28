package com.example.mystore.ui.shoppingcart.layouts

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.example.mystore.R
import com.example.mystore.model.ShoppingCartModel
import com.example.mystore.ui.shared.CustomDivider
import com.example.mystore.ui.theme.Black
import com.example.mystore.ui.theme.Gray
import com.example.mystore.util.convertToUsCurrency

@Composable
fun ShoppingCartRow(
    cartModel: ShoppingCartModel,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = rememberAsyncImagePainter(cartModel.image),
            contentDescription = stringResource(id = R.string.product_label),
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.shopping_cart_icon))
                .width(dimensionResource(id = R.dimen.shopping_cart_icon))
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_medium)))
        )

        Column(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium_horizontal))) {
            Text(
                text = cartModel.title,
                color = Black,
                style = MaterialTheme.typography.labelMedium,
            )

            Text(
                text = cartModel.price.convertToUsCurrency(),
                color = Black,
                style = MaterialTheme.typography.labelMedium,
            )

            Text(
                text = "Quantity: ${cartModel.quantity}",
                color = Black,
                style = MaterialTheme.typography.labelSmall,
            )
        }
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = stringResource(id = R.string.delete_label),
                tint = Gray,
                modifier = Modifier
                    .clickable { onClick() })
        }
    }
    CustomDivider(
        modifier = Modifier
            .padding(vertical = dimensionResource(id = R.dimen.padding_small_15))
    )
}