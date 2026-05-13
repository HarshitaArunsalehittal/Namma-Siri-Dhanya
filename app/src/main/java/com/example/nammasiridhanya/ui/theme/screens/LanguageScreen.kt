package com.example.nammasiridhanya.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nammasiridhanya.R
import com.example.nammasiridhanya.utils.LanguageManager

@Composable
fun LanguageScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.choose_language)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                LanguageManager.setLocale(
                    navController.context,
                    "en"
                )

                navController.navigate("home")
            }
        ) {
            Text(stringResource(R.string.english))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                LanguageManager.setLocale(
                    navController.context,
                    "kn"
                )

                navController.navigate("home")
            }
        ) {
            Text(stringResource(R.string.kannada))
        }
    }
}