package com.example.mystore.ui.shoppingcart.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mystore.R
import com.example.mystore.ui.theme.Black
import com.example.mystore.ui.theme.Purple40

@Composable
fun EmptyShoppingCart() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_medium_horizontal)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = Icons.Outlined.AddShoppingCart,
            tint = Purple40,
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = R.dimen.shopping_cart_icon))
        )

        Text(
            text = stringResource(R.string.empty_cart_label),
            style = MaterialTheme.typography.titleMedium,
            color = Black
        )
    }
}