package com.example.mystore.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mystore.R
import com.example.mystore.ui.home.layouts.Filter
import com.example.mystore.ui.home.layouts.ProductRow
import com.example.mystore.ui.home.layouts.Search
import com.example.mystore.ui.shared.CustomTopBar
import com.example.mystore.ui.shared.LoadingBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    var input by remember { mutableStateOf("") }

    Scaffold(topBar = { CustomTopBar(title = stringResource(id = R.string.home_page_title)) }
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
                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium_20))
        ) {
            Search(
                input = input,
                onInputChanged = { input = it }
            ) { input = "" }

            Filter()
            Box(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.products_top))) {
                LoadingBar(isDisplayed = viewModel.isLoading)

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(viewModel.filterProducts(input)) {
                        ProductRow(it, navController)
                    }
                }
            }
        }
    }
}