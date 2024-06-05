package com.example.retailcloudtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.retailcloudtest.ui.cart.CartScreen
import com.example.retailcloudtest.ui.home.ItemListScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.ItemList.route) {
        composable(Screen.ItemList.route) { backStackEntry ->
            ItemListScreen(navController = navController)
        }
        composable(Screen.Cart.route) { backStackEntry ->
            CartScreen(navController = navController)
        }
    }
}