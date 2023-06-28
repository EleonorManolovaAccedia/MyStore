package com.example.mystore.ui.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.model.ProductModel
import com.example.mystore.model.ShoppingCartModel
import com.example.mystore.repository.IDataStoreRepository
import com.example.mystore.util.Constants.ERROR_MESSAGE
import com.example.mystore.util.Constants.SUCCESS_MESSAGE_ADD_TO_CART
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val dataStoreRepository: IDataStoreRepository
) : ViewModel() {
    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private var stock = 0
    var hasStock by mutableStateOf(false)
    var shoppingCartCount by mutableIntStateOf(0)
    fun addProductToCart(product: ProductModel) {
        if (stock > 0) {
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
                stock -= 1
                hasStock = stock >= 1
                shoppingCartCount = currentCart.count()
                sendMessage(SUCCESS_MESSAGE_ADD_TO_CART)
            } else {
                sendMessage(ERROR_MESSAGE)
            }
        } else {
            sendMessage(ERROR_MESSAGE)
        }
    }

    fun setStock(product: ProductModel) {
        stock = product.stock

        if (stock > 0) {
            val cartItems = dataStoreRepository.getShoppingCart()
            val cartItem = cartItems.firstOrNull { it.productId == product.id }
            cartItem?.let {
                stock -= it.quantity
            }
        }

        hasStock = stock > 0
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch {
            _toastMessage.emit(message)
        }
    }
}