package com.example.mystore.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.mystore.R
import com.example.mystore.ui.theme.Gray

@Composable
fun LoadingBar(isDisplayed: Boolean) {
    if (isDisplayed) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.padding_medium_horizontal)),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = Gray
            )
        }
    }
}