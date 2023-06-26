package com.example.mystore.ui.home.filters

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mystore.R
import com.example.mystore.model.CategoryDetailsModel
import com.example.mystore.ui.theme.Gray
import com.example.mystore.ui.theme.Black
import com.example.mystore.ui.theme.DarkPurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableList(
    data: List<CategoryDetailsModel>,
    selectedCategories: List<String>,
    onFiltersChanged: (CategoryDetailsModel) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val rotateState = animateFloatAsState(
        targetValue = if (expanded) 180F else 0F,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Card(
            onClick = { expanded = !expanded },
            shape = RectangleShape,
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                var selectedCategoriesString = stringResource(id = R.string.all_categories_filter)
                val filteredCategories = data.filter { selectedCategories.contains(it.name) }
                if (filteredCategories.isNotEmpty() && filteredCategories.count() != data.count()) {
                    selectedCategoriesString = filteredCategories.joinToString { it.name }
                }
                Column {
                    Text(
                        text = stringResource(id = R.string.category),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Black
                    )
                    Text(
                        text = selectedCategoriesString,
                        style = MaterialTheme.typography.labelMedium,
                        color = Gray
                    )
                }
                Icon(
                    Icons.Outlined.ArrowDropDown, "",
                    modifier = Modifier
                        .rotate(rotateState.value)
                        .size(dimensionResource(id = R.dimen.arrow_drop_down)),
                    tint = Gray
                )
            }
        }

        AnimatedVisibility(
            visible = expanded,
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(data) { category ->
                    Row(
                        modifier = Modifier
                           .padding(dimensionResource(id = R.dimen.padding_medium_20))
                            .fillMaxWidth()
                            .clickable { onFiltersChanged(category) },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = CenterVertically
                    ) {
                        Text(
                            text = category.name,
                            style = MaterialTheme.typography.labelMedium,
                            color = Black
                        )
                        if (selectedCategories.contains(category.name)) {
                            Icon(
                                Icons.Outlined.Check,
                                "",
                                tint = DarkPurple
                            )
                        }
                    }
                }
            }
        }
    }
}