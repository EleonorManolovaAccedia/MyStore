package com.example.mystore.ui.orderhistory

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mystore.R
import com.example.mystore.ui.orderhistory.layouts.OrderRow
import com.example.mystore.ui.shared.CustomDivider
import com.example.mystore.ui.shared.CustomTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun OrderHistoryScreen(
    destinationsNavigator: DestinationsNavigator
) {
    val viewModel: OrderHistoryViewModel = hiltViewModel()

    Scaffold(topBar = {
        CustomTopBar(
            title = stringResource(id = R.string.order_history_title),
            destinationsNavigator = destinationsNavigator,
            showShoppingCart = false,
            showProfile = false,
            canGoBack = true
        )
    }) { paddingValues ->
        Image(
            painter = painterResource(id = R.drawable.ethereal_artefacts_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium_horizontal))
                .padding(vertical = dimensionResource(id = R.dimen.padding_large)),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                    .padding(bottom = dimensionResource(id = R.dimen.padding_large))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    items(viewModel.orders.toList()) { order ->
                        OrderRow(order)
                    }
                }
            }
            CustomDivider(
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}