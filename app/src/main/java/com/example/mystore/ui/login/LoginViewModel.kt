package com.example.mystore.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mystore.model.LoginModel
import com.example.mystore.model.LoginResponse
import com.example.mystore.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {
    var response: Response<LoginResponse>? by mutableStateOf(null)
    var loginResponse: LoginResponse? by mutableStateOf(null)

    suspend fun login(loginModel: LoginModel) {
        try {
            response = repository.login(loginModel)
            response?.let {
                if (it.isSuccessful) {
                    it.body()?.let { body ->
                        repository.saveToken(body.jwt)
                        loginResponse = body
                    }
                }
            }
        } catch (e: Exception) {
            println("Exception: $e")
        }
    }
}