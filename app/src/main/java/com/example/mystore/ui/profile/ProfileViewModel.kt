package com.example.mystore.ui.profile

import androidx.lifecycle.ViewModel
import com.example.mystore.TokenManager
import com.example.mystore.repository.IDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStoreRepository: IDataStoreRepository,
    private val tokenManager: TokenManager,
) : ViewModel() {

    fun getEmail(): String {
        return dataStoreRepository.getItem("email") ?: ""
    }

    fun logout(onLogout: () -> Unit = {}){
        tokenManager.clearToken()
        dataStoreRepository.clearItem("orders")
        onLogout()
    }
}