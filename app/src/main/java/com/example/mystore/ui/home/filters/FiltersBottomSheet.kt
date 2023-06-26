package com.example.mystore.ui.home.filters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mystore.R
import com.example.mystore.model.CategoryDetailsModel
import com.example.mystore.model.FiltersModel
import com.example.mystore.ui.products.layouts.RatingBar
import com.example.mystore.ui.theme.Black
import com.example.mystore.ui.theme.DarkGray
import com.example.mystore.ui.theme.LightGray20
import com.example.mystore.ui.theme.LightGray40
import com.example.mystore.ui.theme.LightGray60
import com.example.mystore.ui.theme.Purple60
import com.example.mystore.ui.theme.White
import com.example.mystore.util.Constants.MAX_PRICE
import com.example.mystore.util.Constants.MIN_PRICE
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersBottomSheet(
    sheetState: SheetState,
    currentFilters: FiltersModel,
    categories: List<CategoryDetailsModel>,
    onFiltersSave: (FiltersModel) -> Unit
) {
    val scope = rememberCoroutineScope()

    var rating by remember { mutableIntStateOf(currentFilters.rating) }
    var range by remember { mutableStateOf(currentFilters.priceStart..currentFilters.priceEnd) }
    val selectedCategories =
        remember { mutableStateListOf(*currentFilters.selectedCategories.toTypedArray()) }

    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        containerColor = White,
        onDismissRequest = { scope.launch { sheetState.hide() } },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_medium_20),
                end = dimensionResource(id = R.dimen.padding_medium_20)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { scope.launch { sheetState.hide() } },
                        modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size))

                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = "Clear icon",
                            tint = DarkGray
                        )
                    }
                    Text(
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_small)),
                        text = stringResource(id = R.string.filters),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Black
                    )
                }

                Text(
                    text = stringResource(id = R.string.save),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Purple60,
                    modifier = Modifier
                        .clickable {
                            onFiltersSave(
                                FiltersModel(
                                    range.start.roundToInt().toFloat(),
                                    range.endInclusive.roundToInt().toFloat(),
                                    rating,
                                    selectedCategories
                                )
                            )

                            scope.launch { sheetState.hide() }
                        }
                )
            }

            Column(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large))) {
                ExpandableList(categories, selectedCategories) { category ->
                    if (selectedCategories.contains(category.name)) {
                        selectedCategories -= category.name
                    } else {
                        selectedCategories += category.name
                    }
                }
            }

            Divider(
                color = LightGray20,
                thickness = dimensionResource(id = R.dimen.thickness_small),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.padding_large))
            )
            Text(
                text = stringResource(id = R.string.price_range),
                style = MaterialTheme.typography.bodyLarge,
                color = Black,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large))
            )
            RangeSlider(
                range = range,
                valueRange = MIN_PRICE..MAX_PRICE
            ) {
                range = it.start.roundToInt().toFloat()..it.endInclusive.roundToInt().toFloat()
            }
            Divider(
                color = LightGray40,
                thickness = dimensionResource(id = R.dimen.thickness_small),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.padding_small_15))
                    .padding(bottom = dimensionResource(id = R.dimen.padding_small_15))
            )
            Divider(
                color = LightGray60,
                thickness = dimensionResource(id = R.dimen.thickness_small),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.padding_large))
            )
            Text(
                text = stringResource(id = R.string.product_rating),
                style = MaterialTheme.typography.bodyLarge,
                color = Black,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large))
            )
            RatingBar(
                rating = rating,
                onChange = { rating = it },
                canChange = true,
            )
        }
    }
}