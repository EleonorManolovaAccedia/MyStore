package com.example.mystore.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.IntOffset
import com.example.mystore.R
import com.example.mystore.ui.theme.Purple40
import com.example.mystore.ui.theme.White

@Composable
fun IconWithCircle(icon: ImageVector, text: String, onClick: () -> Unit = {}) {
    IconButton(onClick = { onClick() }) {
        Icon(
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
            imageVector = icon,
            tint = Black,
            contentDescription = null
        )
        Text(
            text = text,
            color = White,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .offset {
                    IntOffset(x = +20, y = -20)
                }
                .drawBehind {
                    drawCircle(
                        color = Purple40,
                        radius = this.size.minDimension
                    )
                }
        )
    }
}