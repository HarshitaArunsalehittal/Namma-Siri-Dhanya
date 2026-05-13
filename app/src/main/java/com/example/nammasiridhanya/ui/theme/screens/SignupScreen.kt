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
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun SignupScreen(navController: NavController) {

    var name by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var selectedRole by remember {
        mutableStateOf("Farmer")
    }

    var district by remember {
        mutableStateOf("")
    }

    var milletProduced by remember {
        mutableStateOf("")
    }

    var quantity by remember {
        mutableStateOf("")
    }

    val auth = FirebaseAuth.getInstance()

    val context = LocalContext.current

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF2E7D32),
            Color(0xFFA5D6A7)
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
            text = "Create Account",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
            },
            label = {
                Text("Phone Number")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text("Password")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Select Role",
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {

            RadioButton(
                selected = selectedRole == "Farmer",
                onClick = {
                    selectedRole = "Farmer"
                }
            )

            Text(
                text = "Farmer",
                color = Color.White,
                modifier = Modifier.padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.width(20.dp))

            RadioButton(
                selected = selectedRole == "Consumer",
                onClick = {
                    selectedRole = "Consumer"
                }
            )

            Text(
                text = "Consumer",
                color = Color.White,
                modifier = Modifier.padding(top = 12.dp)
            )
        }

        if (selectedRole == "Farmer") {

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = district,
                onValueChange = {
                    district = it
                },
                label = {
                    Text("District")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = milletProduced,
                onValueChange = {
                    milletProduced = it
                },
                label = {
                    Text("Millets Produced")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = quantity,
                onValueChange = {
                    quantity = it
                },
                label = {
                    Text("Quantity Available")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = {

                auth.createUserWithEmailAndPassword(
                    email,
                    password
                ).addOnCompleteListener {

                    if (it.isSuccessful) {

                        val db =
                            FirebaseFirestore.getInstance()

                        val userData = hashMapOf(

                            "name" to name,

                            "phone" to phone,

                            "email" to email,

                            "role" to selectedRole
                        )

                        db.collection("users")
                            .document(auth.currentUser!!.uid)
                            .set(userData)

                        if (selectedRole == "Farmer") {

                            val farmerData = hashMapOf(

                                "name" to name,

                                "phone" to phone,

                                "email" to email,

                                "district" to district,

                                "millet" to milletProduced,

                                "quantity" to quantity
                            )

                            db.collection("farmers")
                                .document(auth.currentUser!!.uid)
                                .set(farmerData)
                        }

                        Toast.makeText(
                            context,
                            "Signup Successful",
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

            Text(
                "Signup",
                color = Color.Black
            )
        }

        TextButton(
            onClick = {
                navController.navigate("login")
            }
        ) {

            Text(
                "Already have an account? Login",
                color = Color.White
            )
        }
    }
}