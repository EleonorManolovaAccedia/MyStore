package com.example.mystore.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.rounded.Receipt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mystore.R
import com.example.mystore.ui.NavGraphs
import com.example.mystore.ui.destinations.LoginScreenDestination
import com.example.mystore.ui.destinations.OrderHistoryScreenDestination
import com.example.mystore.ui.main.ProfileNavGraph
import com.example.mystore.ui.profile.layouts.ProfilePicture
import com.example.mystore.ui.shared.CustomDivider
import com.example.mystore.ui.shared.CustomTopBar
import com.example.mystore.ui.shared.SubmitButton
import com.example.mystore.ui.theme.Black
import com.example.mystore.ui.theme.Gray
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@ProfileNavGraph(start = true)
@Destination
@Composable
fun ProfileScreen(
    destinationsNavigator: DestinationsNavigator
) {
    val viewModel: ProfileViewModel = hiltViewModel()
    fun navigateToLogin() {
        val hasLoginDestination = destinationsNavigator.popBackStack(
            route = LoginScreenDestination,
            inclusive = false
        )

        if (!hasLoginDestination) {
            destinationsNavigator.navigate(LoginScreenDestination) {
                popUpTo(NavGraphs.home) {
                    inclusive = true
                }
            }
        }
    }

    Scaffold(topBar = {
        CustomTopBar(
            title = stringResource(id = R.string.profile_page_title),
            destinationsNavigator = destinationsNavigator,
            showShoppingCart = false,
            showProfile = false,
            canGoBack = true
        )
    }
    ) { paddingValues ->
        Image(
            painter = painterResource(id = R.drawable.ethereal_artefacts_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium_horizontal))
                .padding(bottom = dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(id = R.dimen.padding_large))
                ) {
                    ProfilePicture(
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_medium_horizontal))
                    )
                    Text(
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_medium_horizontal)),
                        text = viewModel.getEmail(),
                        color = Black,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }
                CustomDivider(
                    modifier = Modifier
                        .padding(vertical = dimensionResource(id = R.dimen.padding_small))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_small)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Rounded.Receipt,
                            contentDescription = "Receipt",
                            tint = Gray,
                            modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_small))
                        )
                        Text(
                            text = stringResource(id = R.string.order_history_label),
                            color = Black,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                    IconButton(onClick = {
                        destinationsNavigator.navigate(
                            OrderHistoryScreenDestination
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.ChevronRight,
                            contentDescription = "Details",
                            tint = Gray
                        )
                    }
                }
            }
            SubmitButton(
                enabled = true,
                onClick = {
                    viewModel.logout(onLogout = { navigateToLogin() })
                },
                buttonText = stringResource(id = R.string.log_out_label)
            )
        }
    }
}
