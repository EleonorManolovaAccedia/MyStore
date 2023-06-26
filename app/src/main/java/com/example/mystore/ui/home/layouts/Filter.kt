package com.example.mystore.ui.home.layouts

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mystore.R
import com.example.mystore.ui.shared.IconWithCircle

@Composable
fun Filter() {
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

        IconWithCircle(icon = Icons.Filled.FilterList, text = "3")
    }
}