
package com.example.nammasiridhanya.ui.theme.screens
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1B5E20),
            Color(0xFF81C784)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email or Phone") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {

                        if (it.isSuccessful) {

                            Toast.makeText(
                                context,
                                "Login Successful",
                                Toast.LENGTH_SHORT
                            ).show()

                            navController.navigate("home")

                        } else {

                            Toast.makeText(
                                context,
                                it.exception?.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login", color = Color.Black)
        }

        TextButton(onClick = {
            navController.navigate("signup")
        }) {
            Text(
                "New User? Register",
                color = Color.White
            )
        }
    }
}