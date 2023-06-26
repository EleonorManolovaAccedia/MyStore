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
import androidx.navigation.NavController
import com.example.mystore.R
import com.example.mystore.ui.theme.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    navController: NavController? = null,
    title: String,
    canGoBack: Boolean = false
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
            navController?.let {
                if (canGoBack)
                    IconButton(onClick = { it.navigateUp() }) {
                        Icon(
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.icon_size_large))
                                .padding(dimensionResource(id = R.dimen.padding_extra_small)),
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
            }
        },
        actions = {
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
            Box(modifier = Modifier
                .clickable { }
                .padding(dimensionResource(id = R.dimen.padding_small_15))) {
                IconWithCircle(icon = Icons.Outlined.ShoppingCart, text = "4")
            }
        }
    )
}