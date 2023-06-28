package com.example.mystore.ui.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.model.ShoppingCartModel
import com.example.mystore.repository.IDataStoreRepository
import com.example.mystore.ui.destinations.ProductDetailsScreenDestination
import com.example.mystore.util.ProductMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val dataStoreRepository: IDataStoreRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var args= ProductDetailsScreenDestination.argsFrom(savedStateHandle)
    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    var stock = MutableStateFlow(0)
    var product = args.product
    val hasStock get() = stock.value > 0
    var shoppingCartCount by mutableIntStateOf(0)

    init {
        setStock()
    }

    fun addProductToCart() {
        if (hasStock) {
            val currentCart = dataStoreRepository.getShoppingCart().toMutableList()
            val element = currentCart.find { it.productId == product.id }
            element?.let {
                currentCart[currentCart.indexOf(it)].quantity += 1
            } ?: run {
                currentCart.add(
                    ShoppingCartModel(
                        product.id,
                        product.title,
                        product.image,
                        product.price.toDouble(),
                        1
                    )
                )
            }

            val result = dataStoreRepository.saveShoppingCart(currentCart)
            if (result) {
                stock.value -= 1
                shoppingCartCount = currentCart.count()
                sendMessage(ProductMessage.SUCCESS_MESSAGE_ADD_TO_CART)
            } else {
                sendMessage(ProductMessage.ERROR_MESSAGE)
            }
        } else {
            sendMessage(ProductMessage.ERROR_MESSAGE_OUT_OF_STOCK)
        }
    }

    private fun setStock() {
        stock.value = product.stock

        if (hasStock) {
            val cartItems = dataStoreRepository.getShoppingCart()
            val cartItem = cartItems.firstOrNull { it.productId == product.id }
            cartItem?.let {
                stock.value -= it.quantity
            }
        }
    }

    private fun sendMessage(productMessage: ProductMessage) {
        viewModelScope.launch {
            _toastMessage.emit(productMessage.messages)
        }
    }
}