package com.example.mystore.ui.home.layouts

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.mystore.R
import com.example.mystore.ui.theme.Gray
import com.example.mystore.ui.theme.LightPink
import com.example.mystore.ui.theme.Shape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    input: String,
    onInputChanged: (String) -> Unit,
    onInputCleared: () -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.search_size)),
        value = input,
        onValueChange = {
            onInputChanged(it)
        },
        placeholder = { Text(stringResource(R.string.search_placeholder)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        shape = Shape.large,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = LightPink,
            textColor = Gray,
        ),
        textStyle = MaterialTheme.typography.labelMedium,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search icon"
            )
        },
        trailingIcon = {
            if (input.isNotEmpty()) {
                IconButton(onClick = { onInputCleared() }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "Clear icon"
                    )
                }
            }
        }
    )
}