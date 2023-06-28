package com.example.mystore.ui.shoppingcart

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.model.OrderModel
import com.example.mystore.model.ShoppingCartModel
import com.example.mystore.repository.IDataStoreRepository
import com.example.mystore.util.Constants.ERROR_MESSAGE
import com.example.mystore.util.Constants.ERROR_MESSAGE_CHECKOUT
import com.example.mystore.util.Constants.SUCCESS_MESSAGE_CHECKOUT
import com.example.mystore.util.Constants.SUCCESS_MESSAGE_REMOVE_FROM_CART
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val dataStoreRepository: IDataStoreRepository
) : ViewModel() {
    val cartItems = mutableStateListOf<ShoppingCartModel>()

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    init {
        getProductsFromCart()
    }

    fun checkout(onSuccess: () -> Unit = {}) {
        if (dataStoreRepository.saveOrder(OrderModel(Date(), cartItems))) {
            dataStoreRepository.clearItem("cart")
            sendMessage(SUCCESS_MESSAGE_CHECKOUT)
            onSuccess.invoke()
        } else {
            sendMessage(ERROR_MESSAGE_CHECKOUT)
        }
    }

    fun removeProductFromCart(cartItem: ShoppingCartModel) {
        cartItems.remove(cartItem)
        val result = dataStoreRepository.saveShoppingCart(cartItems)
        if (result) {
            sendMessage(SUCCESS_MESSAGE_REMOVE_FROM_CART)
        } else {
            cartItems.add(cartItem)
            sendMessage(ERROR_MESSAGE)
        }
    }

    private fun getProductsFromCart() {
        cartItems.apply {
            clear()
            addAll(dataStoreRepository.getShoppingCart())
        }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch {
            _toastMessage.emit(message)
        }
    }
}