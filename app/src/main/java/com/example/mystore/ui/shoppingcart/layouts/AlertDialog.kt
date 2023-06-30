package com.example.mystore.ui.shoppingcart.layouts

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mystore.R

@Composable
fun AlertDialog(
    title: String,
    description: String,
    onChange: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
            onChange(false)
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(description)
        },
        dismissButton = {
            Button(
                onClick = {
                    onChange(false)
                }) {
                Text(stringResource(id = R.string.no_label))
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onChange(false)
                    onClick()
                }) {
                Text(stringResource(id = R.string.yes_label))
            }
        }
    )
}