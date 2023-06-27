package com.example.mystore.ui.home.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import com.example.mystore.ui.theme.Black
import com.example.mystore.ui.theme.DarkPurple

@Composable
fun DropDownRow(
    modifier: Modifier,
    name: String,
    selectedCategories: List<String>
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            color = Black
        )
        if (selectedCategories.contains(name)) {
            Icon(
                Icons.Outlined.Check,
                "",
                tint = DarkPurple
            )
        }
    }
}