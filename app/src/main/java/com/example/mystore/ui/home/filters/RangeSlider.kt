package com.example.mystore.ui.home.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mystore.ui.theme.Black
import com.example.mystore.util.convertToUsCurrency

@Composable
fun RangeSlider(
    range: ClosedFloatingPointRange<Float>,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit
) {

    RangeSlider(
        value = range,
        onValueChange = onValueChange,
        valueRange = valueRange,
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = range.start.toDouble().convertToUsCurrency(),
            color = Black,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = range.endInclusive.toDouble().convertToUsCurrency(),
            color = Black,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}