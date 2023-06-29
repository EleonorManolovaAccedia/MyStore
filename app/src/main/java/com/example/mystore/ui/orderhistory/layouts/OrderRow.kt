package com.example.mystore.ui.orderhistory.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mystore.R
import com.example.mystore.model.OrderModel
import com.example.mystore.ui.shared.CustomDivider
import com.example.mystore.ui.theme.Black
import com.example.mystore.ui.theme.Gray
import com.example.mystore.util.convertToUsCurrency
import com.example.mystore.util.prase

@Composable
fun OrderRow(orderModel: OrderModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = orderModel.items.size.toString() + (" " + if (orderModel.items.size > 1)
                    "items" else "item"),
                color = Black,
                style = MaterialTheme.typography.labelMedium,
            )

            Text(
                text = "${stringResource(id = R.string.date_label)}: ${orderModel.orderDate.prase("dd.MM.yyyy")}",
                color = Gray,
                style = MaterialTheme.typography.labelSmall,
            )
        }

        Text(
            text = "${stringResource(id = R.string.total_label)}: ${
                orderModel.items.sumOf { it.price * it.quantity }.convertToUsCurrency()
            }",
            color = Black,
            style = MaterialTheme.typography.labelMedium,
        )
    }
    CustomDivider(
        modifier = Modifier
            .padding(vertical = dimensionResource(id = R.dimen.padding_small_15))
    )
}