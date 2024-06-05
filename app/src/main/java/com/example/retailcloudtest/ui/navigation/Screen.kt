package com.example.retailcloudtest.ui.navigation

sealed class Screen(val route: String) {
    object ItemList : Screen("home")
    object Cart : Screen("cart")
}
