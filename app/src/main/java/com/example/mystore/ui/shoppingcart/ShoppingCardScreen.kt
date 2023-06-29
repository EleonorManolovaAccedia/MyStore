package com.example.mystore.ui.shoppingcart

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mystore.R
import com.example.mystore.ui.NavGraphs
import com.example.mystore.ui.main.ShoppingCartNavGraph
import com.example.mystore.ui.shared.CustomTopBar
import com.example.mystore.ui.shared.SubmitButton
import com.example.mystore.ui.shoppingcart.layouts.AlertDialog
import com.example.mystore.ui.shoppingcart.layouts.EmptyShoppingCart
import com.example.mystore.ui.shoppingcart.layouts.ShoppingCartRow
import com.example.mystore.ui.theme.Black
import com.example.mystore.util.Constants.CHECK_OUT_DESCRIPTION
import com.example.mystore.util.Constants.CHECK_OUT_TITLE
import com.example.mystore.util.convertToUsCurrency
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ShoppingCartNavGraph(start = true)
@Destination
@Composable
fun ShoppingCardScreen(destinationsNavigator: DestinationsNavigator) {
    val viewModel: ShoppingCartViewModel = hiltViewModel()
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    val cartItems = viewModel.cartItems.collectAsState()

    LaunchedEffect(Unit) {
        viewModel
            .toastMessage
            .collect { message ->
                Toast.makeText(
                    context,
                    message,
                    Toast.LENGTH_SHORT,
                ).show()
            }
    }

    Scaffold(topBar = {
        CustomTopBar(
            title = stringResource(id = R.string.shopping_card_page_title),
            destinationsNavigator = destinationsNavigator,
            showShoppingCart = false,
            showProfile = false,
            canGoBack = true
        )
    }
    ) { paddingValues ->
        Image(
            painter = painterResource(id = R.drawable.ethereal_artefacts_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        if (cartItems.value.isEmpty()) {
            EmptyShoppingCart()
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_medium_horizontal))
                    .padding(bottom = dimensionResource(id = R.dimen.padding_large)),
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    items(cartItems.value.toList()) { cartItem ->
                        ShoppingCartRow(cartItem) {
                            viewModel.removeProductFromCart(cartItem)
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_large))
                ) {
                    Text(
                        text = "${stringResource(id = R.string.items_label)}: ${cartItems.value.count()}",
                        color = Black,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "${stringResource(id = R.string.total_label)}: ${
                            cartItems.value.sumOf { it.quantity * it.price }.convertToUsCurrency()
                        }",
                        color = Black,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row() {
                    SubmitButton(
                        enabled = cartItems.value.isNotEmpty(),
                        onClick = {
                            openDialog.value = true
                        },
                        buttonText = stringResource(id = R.string.check_out_label)
                    )
                }

                if (openDialog.value) {
                    AlertDialog(
                        title = CHECK_OUT_TITLE,
                        description = CHECK_OUT_DESCRIPTION,
                        onChange = { openDialog.value = it }) {
                        viewModel.checkout()
                        destinationsNavigator.navigate(NavGraphs.home)
                    }
                }
            }
        }
    }
}