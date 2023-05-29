package com.example.mystore.ui.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.mystore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(title: String) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        navigationIcon = {
            IconButton(onClick = {}, enabled = false) {
                Icon(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.icon_size_large))
                        .padding(dimensionResource(id = R.dimen.padding_extra_small)),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            Box(modifier = Modifier
                .clickable { }
                .padding(dimensionResource(id = R.dimen.padding_small_15))) {
                Icon(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.icon_size_large))
                        .padding(dimensionResource(id = R.dimen.padding_extra_small)),
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Cart"
                )
            }
        }
    )
}