package com.example.mystore.ui.shoppingcart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.model.OrderModel
import com.example.mystore.model.ShoppingCartModel
import com.example.mystore.repository.IDataStoreRepository
import com.example.mystore.util.ShoppingCartMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val dataStoreRepository: IDataStoreRepository
) : ViewModel() {
    val cartItems = MutableStateFlow(emptyList<ShoppingCartModel>())

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    init {
        getProductsFromCart()
    }

    fun checkout(onSuccess: () -> Unit = {}) {
        if (dataStoreRepository.saveOrder(OrderModel(Date(), cartItems.value))) {
            dataStoreRepository.clearItem("cart")
            sendMessage(ShoppingCartMessage.SUCCESS_MESSAGE_CHECKOUT)
            onSuccess.invoke()
        } else {
            sendMessage(ShoppingCartMessage.ERROR_MESSAGE_CHECKOUT)
        }
    }

    fun removeProductFromCart(cartItem: ShoppingCartModel) {
        cartItems.update { cartItems -> cartItems.filter { it.productId != cartItem.productId } }
        val result = dataStoreRepository.saveShoppingCart(cartItems.value)
        if (result) {
            sendMessage(ShoppingCartMessage.SUCCESS_MESSAGE_REMOVE_FROM_CART)
        } else {
            cartItems.update { it + cartItem }
            sendMessage(ShoppingCartMessage.ERROR_MESSAGE)
        }
    }

    private fun getProductsFromCart() {
        cartItems.apply {
            cartItems.value = emptyList()
            cartItems.value = (dataStoreRepository.getShoppingCart()).toMutableList()
        }
    }

    private fun sendMessage(message: ShoppingCartMessage) {
        viewModelScope.launch {
            _toastMessage.emit(message.messages)
        }
    }
}