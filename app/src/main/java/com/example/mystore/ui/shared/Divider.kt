package com.example.mystore.ui.shared

import androidx.compose.foundation.layout.fillMaxWidth
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
    modifier: Modifier = Modifier,
    color: Color = LightGray40,
    thickness: Dp = dimensionResource(id = R.dimen.thickness_small)
) {

    Divider(
        color = color,
        thickness = thickness,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    )
}