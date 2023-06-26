package com.example.mystore.ui.main

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import com.example.mystore.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {
    fun hasToken(): Boolean {
        return !TextUtils.isEmpty(tokenManager.getToken())
    }
}