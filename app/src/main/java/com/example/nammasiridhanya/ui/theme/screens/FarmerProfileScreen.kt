package com.example.nammasiridhanya.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun FarmerProfileScreen() {

    var farmerName by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var district by remember {
        mutableStateOf("")
    }

    var millet by remember {
        mutableStateOf("")
    }

    val db = FirebaseFirestore.getInstance()

    val auth = FirebaseAuth.getInstance()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "Farmer Profile",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = farmerName,
            onValueChange = {
                farmerName = it
            },
            label = {
                Text("Farmer Name")
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
            value = millet,
            onValueChange = {
                millet = it
            },
            label = {
                Text("Millets Produced")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                val data = hashMapOf(

                    "name" to farmerName,

                    "phone" to phone,

                    "location" to district,

                    "millet" to millet
                )

                db.collection("farmers")
                    .document(auth.currentUser!!.uid)
                    .set(data)
                    .addOnSuccessListener {

                        Toast.makeText(
                            context,
                            "Farmer Profile Updated",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Save Profile")
        }
    }
}