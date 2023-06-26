package com.example.mystore.ui.home.layouts

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mystore.R
import com.example.mystore.model.CategoryDetailsModel
import com.example.mystore.model.FiltersModel
import com.example.mystore.ui.home.filters.FiltersBottomSheet
import com.example.mystore.ui.shared.IconWithCircle
import com.example.mystore.ui.theme.Black
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Filter(
    filtersCount: Int,
    currentFilters: FiltersModel,
    categories: List<CategoryDetailsModel>,
    onFiltersSave: (FiltersModel) -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    Row(
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.filter_top))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.new_product_title),
            style = MaterialTheme.typography.labelLarge,
            color = Black,
        )

        IconWithCircle(
            icon = Icons.Filled.FilterList,
            text = if (filtersCount > 0) "$filtersCount" else ""
        ) {
            scope.launch { sheetState.show() }
        }
    }

    if (sheetState.isVisible) {
        FiltersBottomSheet(
            sheetState = sheetState,
            currentFilters = currentFilters,
            categories = categories,
            onFiltersSave = onFiltersSave
        )
    }
}