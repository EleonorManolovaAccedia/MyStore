package com.example.mystore.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.TokenManager
import com.example.mystore.model.LoginModel
import com.example.mystore.model.LoginResponse
import com.example.mystore.repository.LoginRepositoryImpl
import com.example.mystore.util.hasDigits
import com.example.mystore.util.isOverSevenCharacters
import com.example.mystore.util.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepositoryImpl,
    private val tokenManager: TokenManager
) : ViewModel() {
    var error: String? by mutableStateOf(null)
    private var loginResponse by mutableStateOf<LoginResponse?>(null)


    fun login(loginModel: LoginModel, onLogin: () -> Unit = {}) {
        viewModelScope.launch {
            try {
                loginResponse = repository.login(loginModel)
                loginResponse?.jwt?.let { jwtToken -> tokenManager.saveToken(jwtToken) }
                onLogin.invoke()
            } catch (e: HttpException) {
                error =
                    (if (e.code() == 400) ("Email or password is invalid") else ("Something went wrong"))
            } catch (e: Throwable) {
                println(e)
            }
        }
    }

    val validateFields: (email: String, password: String) -> Boolean = { email, password ->
        email.isNotBlank() &&
                email.isValidEmail() &&
                password.isOverSevenCharacters() &&
                password.hasDigits()
    }
}