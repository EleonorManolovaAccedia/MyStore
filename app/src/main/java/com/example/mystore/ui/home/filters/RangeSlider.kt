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
import java.text.NumberFormat

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
            text = NumberFormat.getCurrencyInstance()
                .format(range.start),
            color = Black,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = NumberFormat.getCurrencyInstance()
                .format(range.endInclusive),
            color = Black,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}