package com.example.retailcloudtest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retailcloudtest.data.models.CartItem
import com.example.retailcloudtest.data.models.Item
import com.example.retailcloudtest.data.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    private val _cartItems = MutableLiveData<List<CartItem>>()
    val cartItems: LiveData<List<CartItem>> = _cartItems

    init {
        fetchItems()
        getCartItems()
    }

    private fun fetchItems() = viewModelScope.launch {
        _items.value = repository.fetchItems()
    }

    fun addItemToCart(item: Item) = viewModelScope.launch {
        val cartItems = _cartItems.value ?: emptyList()
        val existingItem = cartItems.find { it.itemID == item.itemID }
        if (existingItem != null) {
            repository.insertCartItem(existingItem.copy(quantity = existingItem.quantity + 1))
        } else {
            repository.insertCartItem(
                CartItem(
                    itemID = item.itemID,
                    itemName = item.itemName,
                    sellingPrice = item.sellingPrice,
                    taxPercentage = item.taxPercentage,
                    quantity = 1
                )
            )
        }
        getCartItems()
    }

    private fun getCartItems() = viewModelScope.launch {
        _cartItems.value = repository.getAllCartItems()
    }


    fun clearCart() = viewModelScope.launch {
        repository.clearCart()
        getCartItems()
    }

}