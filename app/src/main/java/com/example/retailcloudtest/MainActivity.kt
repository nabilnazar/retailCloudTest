package com.example.retailcloudtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.retailcloudtest.ui.navigation.NavGraph
import com.example.retailcloudtest.ui.navigation.Screen
import com.example.retailcloudtest.ui.theme.RetailCloudTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetailCloudTestTheme {
                val navController = rememberNavController()
                Scaffold(
                ) { innerPadding ->
                    Surface(
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        NavGraph(navController = navController)
                        HandleBackPress(navController)
                    }
                }
            }
        }
    }

    @Composable
    private fun HandleBackPress(navController: NavHostController) {
        BackHandler {
            if (navController.currentBackStackEntry?.destination?.route == Screen.ItemList.route) {
                // Exit the app if we are on the ItemList screen
                (navController.context as? MainActivity)?.finish()
            } else {
                // Otherwise, pop the back stack
                navController.popBackStack()
            }
        }

    }
}