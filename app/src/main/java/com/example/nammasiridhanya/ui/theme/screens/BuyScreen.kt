package com.example.nammasiridhanya.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore

data class FarmerData(

    val id: String = "",

    val name: String = "",

    val phone: String = "",

    val district: String = "",

    val millet: String = "",

    val quantity: String = ""
)

@Composable
fun BuyScreen() {

    val farmers = remember {
        mutableStateListOf<FarmerData>()
    }

    val db = FirebaseFirestore.getInstance()

    val context = LocalContext.current

    var showOrderDialog by remember {
        mutableStateOf(false)
    }

    var selectedFarmer by remember {
        mutableStateOf<FarmerData?>(null)
    }

    var quantityNeeded by remember {
        mutableStateOf("")
    }

    var buyerPhone by remember {
        mutableStateOf("")
    }

    var buyerMessage by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {

        db.collection("farmers")
            .get()
            .addOnSuccessListener { result ->

                farmers.clear()

                for (doc in result) {

                    farmers.add(

                        FarmerData(

                            id = doc.id,

                            name =
                                doc.getString("name") ?: "",

                            phone =
                                doc.getString("phone") ?: "",

                            district =
                                doc.getString("district") ?: "",

                            millet =
                                doc.getString("millet") ?: "",

                            quantity =
                                doc.getString("quantity") ?: ""
                        )
                    )
                }
            }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {

            items(farmers) { farmer ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = farmer.name,
                            style =
                                MaterialTheme.typography.titleLarge
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text("Phone: ${farmer.phone}")

                        Text("District: ${farmer.district}")

                        Text("Produces: ${farmer.millet}")

                        Text(
                            "Available: ${farmer.quantity}"
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Button(
                            onClick = {

                                selectedFarmer = farmer

                                showOrderDialog = true
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text("Order Now")
                        }
                    }
                }
            }
        }

        if (
            showOrderDialog &&
            selectedFarmer != null
        ) {

            AlertDialog(

                onDismissRequest = {

                    showOrderDialog = false
                },

                confirmButton = {

                    Button(
                        onClick = {

                            val order = hashMapOf(

                                "farmerId" to
                                        selectedFarmer!!.id,

                                "farmerName" to
                                        selectedFarmer!!.name,

                                "millet" to
                                        selectedFarmer!!.millet,

                                "quantity" to
                                        quantityNeeded,

                                "buyerPhone" to
                                        buyerPhone,

                                "message" to
                                        buyerMessage
                            )

                            db.collection("orders")
                                .add(order)

                            Toast.makeText(
                                context,
                                "Order Placed Successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                            showOrderDialog = false
                        }
                    ) {

                        Text("Place Order")
                    }
                },

                dismissButton = {

                    Button(
                        onClick = {

                            showOrderDialog = false
                        }
                    ) {

                        Text("Cancel")
                    }
                },

                title = {

                    Text("Order Millet")
                },

                text = {

                    Column {

                        Text(
                            "Farmer: ${selectedFarmer!!.name}"
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Text(
                            "Millet: ${selectedFarmer!!.millet}"
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        OutlinedTextField(
                            value = quantityNeeded,

                            onValueChange = {

                                quantityNeeded = it
                            },

                            label = {

                                Text("Quantity Needed")
                            },

                            modifier =
                                Modifier.fillMaxWidth()
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        OutlinedTextField(
                            value = buyerPhone,

                            onValueChange = {

                                buyerPhone = it
                            },

                            label = {

                                Text("Buyer Phone")
                            },

                            modifier =
                                Modifier.fillMaxWidth()
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        OutlinedTextField(
                            value = buyerMessage,

                            onValueChange = {

                                buyerMessage = it
                            },

                            label = {

                                Text("Message")
                            },

                            modifier =
                                Modifier.fillMaxWidth()
                        )
                    }
                }
            )
        }
    }
}