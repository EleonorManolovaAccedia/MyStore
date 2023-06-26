package com.example.mystore.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mystore.R
import com.example.mystore.util.hasDigits
import com.example.mystore.util.isOverSevenCharacters
import com.example.mystore.model.LoginModel
import com.example.mystore.ui.NavGraphs
import com.example.mystore.ui.shared.SubmitButton
import com.example.mystore.ui.theme.DarkPurple
import com.example.mystore.ui.theme.DarkRed
import com.example.mystore.ui.theme.White
import com.example.mystore.util.isValidEmail
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(destinationsNavigator: DestinationsNavigator) {
    var emailField by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }
    var passwordField by remember { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    var isButtonEnabled by remember { mutableStateOf(true) }
    val snackbarHostState = remember { SnackbarHostState() }
    val loginViewModel: LoginViewModel = hiltViewModel()

    LaunchedEffect(loginViewModel.error) {
        loginViewModel.error?.let {
            snackbarHostState.showSnackbar(it)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ethereal_artefacts_login_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.padding_top_logo)),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.logo_width))
                        .height(dimensionResource(id = R.dimen.logo_height)),
                    painter = painterResource(R.drawable.ethereal_artefacts_logo),
                    contentDescription = stringResource(id = R.string.background_image_description),
                    alignment = Alignment.TopCenter
                )
            }
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium_25))
                    .padding(top = dimensionResource(id = R.dimen.padding_top_title))
            ) {
                Text(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_bottom_title)),
                    text = stringResource(id = R.string.login_details_title),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = DarkPurple,
                        textAlign = TextAlign.Center
                    )
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(id = R.dimen.padding_bottom_large)),
                    value = emailField,
                    onValueChange = {
                        emailField = it
                        isEmailValid = it.isValidEmail()
                    },
                    isError = !isEmailValid && emailField.isNotEmpty(),
                    placeholder = { Text(stringResource(id = R.string.email_label)) },
                    label = { Text(stringResource(id = R.string.email_label)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    supportingText = {
                        if (!isEmailValid && emailField.isNotEmpty()) {
                            Text(text = "Invalid email", color = DarkRed)
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = White,
                    )

                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(id = R.dimen.padding_bottom_large)),
                    value = passwordField,
                    onValueChange = { passwordField = it },
                    placeholder = { Text(stringResource(id = R.string.password_label)) },
                    label = { Text(stringResource(id = R.string.password_label)) },
                    singleLine = true,
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    trailingIcon = {
                        val image = if (passwordVisible.value)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff
                        val description =
                            if (passwordVisible.value) "Hide password" else "Show password"

                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(imageVector = image, description)
                        }
                    },
                    supportingText = {
                        if ((!passwordField.isOverSevenCharacters() ||
                                    !passwordField.hasDigits()) &&
                            passwordField.isNotEmpty()
                        ) {
                            Text(text = "Invalid password", color = DarkRed)
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = White,
                    )
                )
                SubmitButton(
                    enabled = loginViewModel.validateFields(
                        emailField,
                        passwordField
                    ) && isButtonEnabled,
                    onClick = {
                        isButtonEnabled = false
                        loginViewModel.login(LoginModel(emailField, passwordField))
                        { destinationsNavigator.navigate(NavGraphs.home) }
                        isButtonEnabled = true
                        passwordField = ""
                    },
                    buttonText = stringResource(id = R.string.login_details_title),
                )
            }
        }
    }
}
