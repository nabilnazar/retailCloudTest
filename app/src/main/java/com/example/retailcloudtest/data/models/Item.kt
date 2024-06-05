package com.example.retailcloudtest.data.models


data class Item(
    val itemID: String,
    val itemName: String,
    val sellingPrice: Double,
    val taxPercentage: Double
)