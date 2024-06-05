package com.example.retailcloudtest.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.retailcloudtest.ui.home.MainViewModel
import com.example.retailcloudtest.ui.navigation.Screen


@Composable
fun CartScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {
    val cartItems by viewModel.cartItems.observeAsState(emptyList())
    Scaffold(
        topBar ={
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Cart Screen")
            }
        },
        bottomBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp), Arrangement.Absolute.SpaceBetween
                ){
                Button(onClick = { navController.navigate(Screen.ItemList.route) }) {
                    Text(text = "Continue Shopping")
                }
                Button(onClick = {viewModel.clearCart()}) {
                    Text(text = "Clear Cart")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            cartItems.forEach { cartItem ->
                Text(text = "${cartItem.itemName}: $${cartItem.sellingPrice} x ${cartItem.quantity}")
            }

            val subtotal = cartItems.sumOf { it.sellingPrice * it.quantity }
            val tax = cartItems.sumOf { it.sellingPrice * it.quantity * (it.taxPercentage / 100) }
            val total = subtotal + tax

            Text(text = "Subtotal: $${subtotal}")
            Text(text = "Tax: $${tax}")
            Text(text = "Total: $${total}")

        }
    }
}