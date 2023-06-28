package com.example.mystore.ui.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountCircle
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
import com.example.mystore.ui.NavGraphs
import com.example.mystore.ui.theme.Gray
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    destinationsNavigator: DestinationsNavigator,
    canGoBack: Boolean = false,
    showShoppingCart: Boolean = false,
    showProfile: Boolean = false,
    shoppingCartCount: Int = 0,
) {
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
            if (canGoBack)
                IconButton(onClick = { destinationsNavigator.navigateUp() })
                {
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
            if (showProfile)
                Box(modifier = Modifier
                    .clickable { }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.icon_size)),
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = "Account",
                        tint = Gray
                    )
                }
            if (showShoppingCart)
                Box(modifier = Modifier
                    .clickable { }
                    .padding(dimensionResource(id = R.dimen.padding_small_15))) {
                    IconWithCircle(
                        icon = Icons.Outlined.ShoppingCart,
                        text = if (shoppingCartCount > 0) shoppingCartCount.toString() else "",
                        onClick = { destinationsNavigator.navigate(NavGraphs.shoppingCart) }
                    )
                }
        }
    )
}