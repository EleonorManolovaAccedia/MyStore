package com.example.mystore.ui.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.mystore.R
import com.example.mystore.ui.theme.DarkPurple
import com.example.mystore.ui.theme.LightGray20
import com.example.mystore.ui.theme.LightGray40
import com.example.mystore.ui.theme.Shape
import com.example.mystore.ui.theme.White

@Composable
fun SubmitButton(
    enabled: Boolean,
    onClick: () -> Unit,
    buttonText: String,
    icon: @Composable (() -> Unit)? = null,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkPurple,
            contentColor = White,
            disabledContainerColor = LightGray20,
            disabledContentColor = LightGray40
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation),
            disabledElevation = dimensionResource(id = R.dimen.elevation)
        ),
        shape = Shape.large,
    ) {
        icon?.invoke()
        Text(text = buttonText)
    }
}