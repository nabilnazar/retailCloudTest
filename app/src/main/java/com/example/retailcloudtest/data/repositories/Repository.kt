package com.example.retailcloudtest.data.repositories

import com.example.retailcloudtest.data.dao.ApiService
import com.example.retailcloudtest.data.dao.ItemDao
import com.example.retailcloudtest.data.models.CartItem
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(
    private val apiService: ApiService,
    private val itemDao: ItemDao
) {

    suspend fun fetchItems() = apiService.fetchItems()

    suspend fun insertCartItem(cartItem: CartItem) = itemDao.insert(cartItem)

    suspend fun getAllCartItems() = itemDao.getAllCartItems()

    suspend fun clearCart() = itemDao.clearCart()
}