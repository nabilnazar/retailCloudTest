package com.example.retailcloudtest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.retailcloudtest.data.dao.ItemDao
import com.example.retailcloudtest.data.models.CartItem


@Database(entities = [CartItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}