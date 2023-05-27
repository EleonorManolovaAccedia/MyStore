package com.example.mystore.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mystore.util.hasDigits
import com.example.mystore.util.isOverSevenCharacters
import com.example.mystore.model.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navigateToProductDetailsScreen: () -> Unit = {}) {
    var emailField by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }
    var passwordField by remember { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    var isValidInput by remember { mutableStateOf(false) }
    var isButtonEnabled by remember { mutableStateOf(true) }
    val mContext = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var loginViewModel: LoginViewModel = hiltViewModel()
    val errorMessage = "Your email or password are incorrect"
    val isValidEmail: (email: String) -> Boolean = { email ->
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    val validateFields: (email: String, password: String) -> Boolean = { email, password ->
        email.isNotBlank() &&
                isValidEmail(email) &&
                password.isOverSevenCharacters() &&
                password.hasDigits()
    }
    Column(
        modifier = Modifier
            .padding(30.dp)
            .padding(top = 180.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 20.dp),
            text = "Log in",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            value = emailField,
            onValueChange = {
                emailField = it
                isEmailValid = isValidEmail(it)
            },
            isError = !isEmailValid && emailField.isNotEmpty(),
            placeholder = { Text(text = "Email") },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            supportingText = {
                if (!isEmailValid && emailField.isNotEmpty()) {
                    Text(text = "Invalid email", color = Color.Red)
                }
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            value = passwordField,
            onValueChange = { passwordField = it },
            placeholder = { Text(text = "Password") },
            label = { Text("Password") },
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
                    Text(text = "Invalid password", color = Color.Red)
                }
            })
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                isValidInput = validateFields(emailField, passwordField)
                if (isValidInput) {
                    isButtonEnabled = false
                    val loginModel = LoginModel(emailField, passwordField)
                    coroutineScope.launch(Dispatchers.IO) {
                        loginViewModel.login(loginModel)
                        coroutineScope.launch(Dispatchers.Main) {
                            loginViewModel.response?.let {
                                if (it.isSuccessful) {
                                    navigateToProductDetailsScreen()
                                } else {
                                    val errorBody = it.errorBody()?.string()
                                    if (errorBody != null) {
                                        val jObjError = JSONObject(errorBody)
                                        val errorMessage = jObjError.getJSONObject("error").getString("message")
                                        Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show()
                                    } else {
                                        Toast.makeText(mContext, "Unknown error occurred", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show()
                }
                isButtonEnabled = true
                passwordField = ""
            },
            enabled = validateFields(emailField, passwordField) && isButtonEnabled,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            )
        )
        {
            Text(text = "Log in")
        }
    }
}