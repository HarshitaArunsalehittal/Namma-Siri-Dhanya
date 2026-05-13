package com.example.nammasiridhanya.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nammasiridhanya.ui.theme.screens.AiAssistantScreen
import com.example.nammasiridhanya.ui.theme.screens.BuyScreen
import com.example.nammasiridhanya.ui.theme.screens.HealthScreen
import com.example.nammasiridhanya.ui.theme.screens.HomeScreen
import com.example.nammasiridhanya.ui.theme.screens.LanguageScreen
import com.example.nammasiridhanya.ui.theme.screens.LoginScreen
import com.example.nammasiridhanya.ui.theme.screens.MarketScreen
import com.example.nammasiridhanya.ui.theme.screens.MilletDetailScreen
import com.example.nammasiridhanya.ui.theme.screens.RecipeScreen
import com.example.nammasiridhanya.ui.theme.screens.RoleScreen
import com.example.nammasiridhanya.ui.theme.screens.SignupScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "language"
    ) {

        composable("language") {

            LanguageScreen(navController)
        }

        composable("role") {

            RoleScreen(navController)
        }

        composable("login") {

            LoginScreen(navController)
        }

        composable("signup") {

            SignupScreen(navController)
        }

        composable("home") {

            HomeScreen(navController)
        }

        composable("market") {

            MarketScreen()
        }

        composable("recipes") {

            val favoriteRecipes = remember {

                mutableStateListOf<String>()
            }

            RecipeScreen(favoriteRecipes)
        }

        composable("health") {

            HealthScreen()
        }

        composable("buy") {

            BuyScreen()
        }

        composable("ai") {

            AiAssistantScreen()
        }

        composable(
            "detail/{milletName}"
        ) {

            val milletName =
                it.arguments?.getString("milletName")
                    ?: ""

            MilletDetailScreen(milletName)
        }
    }
}