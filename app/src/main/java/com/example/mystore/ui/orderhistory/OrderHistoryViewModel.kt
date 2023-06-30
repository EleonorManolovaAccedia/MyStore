package com.example.mystore.ui.orderhistory

import androidx.lifecycle.ViewModel
import com.example.mystore.repository.IDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderHistoryViewModel @Inject constructor(
    dataStoreRepository: IDataStoreRepository,
) : ViewModel() {

    var orders = dataStoreRepository.getOrderHistory()
}