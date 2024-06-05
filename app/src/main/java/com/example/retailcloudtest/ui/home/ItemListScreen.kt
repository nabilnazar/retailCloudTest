package com.example.retailcloudtest.ui.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.retailcloudtest.ui.navigation.Screen


@Composable
fun ItemListScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {
    val items by viewModel.items.observeAsState(emptyList())
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("List Items:")
        items.forEach { item ->
          Row(verticalAlignment = Alignment.CenterVertically){
                Text(text = "${item.itemName}: $${item.sellingPrice}")
                Spacer(modifier = Modifier.padding(horizontal = 12.dp))
                Button(onClick = {
                    viewModel.addItemToCart(item)
                    toastMessage(item.itemName,context)
                }) {
                    Text(text = "Add to Cart")
                }
            }
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = { navController.navigate(Screen.Cart.route) }) {
            Text(text = "View Cart")
        }
    }
}

fun toastMessage(itemName: String, context: Context) {
    Toast.makeText(context,"This product has added to cartScreen!!",Toast.LENGTH_SHORT).show()
}
