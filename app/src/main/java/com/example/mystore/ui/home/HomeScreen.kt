package com.example.mystore.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mystore.R
import com.example.mystore.ui.destinations.ProductDetailsScreenDestination
import com.example.mystore.ui.home.layouts.Filter
import com.example.mystore.ui.home.layouts.ProductRow
import com.example.mystore.ui.home.layouts.Search
import com.example.mystore.ui.main.HomeNavGraph
import com.example.mystore.ui.shared.CustomTopBar
import com.example.mystore.ui.shared.LoadingBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@HomeNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(destinationsNavigator: DestinationsNavigator) {
    val viewModel: HomeViewModel = hiltViewModel()

    Scaffold(topBar = {
        CustomTopBar(
            title = stringResource(id = R.string.home_page_title),
            destinationsNavigator = destinationsNavigator,
            showShoppingCart = true,
            showProfile = true,
            shoppingCartCount = viewModel.shoppingCartCount(),
        )
    }
    ) { paddingValues ->
        Image(
            painter = painterResource(id = R.drawable.ethereal_artefacts_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium_horizontal))
        ) {
            Search(
                input = viewModel.input,
                onInputChanged = { viewModel.filterProducts(it) },
                onInputCleared = { viewModel.clearInput() }
            )

            Filter(
                filtersCount = viewModel.filtersCount,
                currentFilters = viewModel.filtersModel,
                categories = viewModel.categories
            ) {
                viewModel.setFilters(it)
            }
            Box(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.products_top))) {
                LoadingBar(isDisplayed = viewModel.isLoading)

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(viewModel.products.toList()) { product ->
                        ProductRow(product) {
                            destinationsNavigator.navigate(
                                ProductDetailsScreenDestination(product)
                            )
                        }
                    }
                }
            }
        }
    }
}