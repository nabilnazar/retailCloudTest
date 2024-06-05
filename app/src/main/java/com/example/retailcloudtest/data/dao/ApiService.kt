package com.example.retailcloudtest.data.dao

import com.example.retailcloudtest.data.models.Item
import com.example.retailcloudtest.util.Constants.BASE_URL
import retrofit2.http.GET

interface ApiService {

    @GET(BASE_URL)
    suspend fun fetchItems(): List<Item>

}