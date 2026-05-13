
package com.example.nammasiridhanya.ui.theme.components
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ShowChart
@Composable
fun BottomBar(navController: NavController) {

    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("home")
            },
            icon = {
                Icon(Icons.Default.Home, null)
            },
            label = {
                Text("Home")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("market")
            },
            icon = {
                Icon(Icons.Default.ShowChart, null)
            },
            label = {
                Text("Mandi")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("recipes")
            },
            icon = {
                Icon(Icons.Default.Restaurant, null)
            },
            label = {
                Text("Recipes")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("health")
            },
            icon = {
                Icon(Icons.Default.Favorite, null)
            },
            label = {
                Text("Health")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("buy")
            },
            icon = {
                Icon(Icons.Default.ShoppingCart, null)
            },
            label = {
                Text("Buy")
            }
        )
    }
}