package com.example.mystore.ui.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.example.mystore.R
import com.example.mystore.ui.theme.LightGray40

@Composable
fun CustomDivider(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.padding_small_15))
        .padding(bottom = dimensionResource(id = R.dimen.padding_small_15)),
    color: Color = LightGray40,
    thickness: Dp = dimensionResource(id = R.dimen.thickness_small)
) {

    Divider(
        color = color,
        thickness = thickness,
        modifier = modifier
    )
}