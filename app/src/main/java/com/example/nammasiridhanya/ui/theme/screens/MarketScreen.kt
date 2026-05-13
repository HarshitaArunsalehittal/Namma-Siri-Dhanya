package com.example.nammasiridhanya.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nammasiridhanya.api.RetrofitInstance
import kotlinx.coroutines.launch

data class Millet(

    val name: String,

    val desc: String,

    val price: Float
)

@Composable
fun MarketScreen() {

    val scope = rememberCoroutineScope()

    val milletList = remember {
        mutableStateListOf<Millet>()
    }

    val apiKey =
        "579b464db66ec23bdd0000017cfe918c08fc4d6a694ae5e5f06ee8fa"

    LaunchedEffect(Unit) {

        scope.launch {

            try {

                val response =
                    RetrofitInstance.api
                        .getPrices(apiKey)

                milletList.clear()

                response.records.forEach { record ->

                    milletList.add(

                        Millet(

                            record.commodity,

                            record.district,

                            record.modal_price.toFloatOrNull()
                                ?: 0f
                        )
                    )
                }





            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }

    var selectedMillet by remember {
        mutableStateOf<Millet?>(null)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    val filteredList = milletList.filter {

        it.name.contains(
            searchText,
            ignoreCase = true
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F2EA))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF3E5F1B))
                .padding(16.dp)
        ) {

            Column {

                Text(
                    "Siri-Dhanya Hub 🌾",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Text(
                    "Empowering Farmers, Nourishing Consumers",
                    color = Color(0xFFDDE5C3),
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = {
                Text("Search millets...")
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null
                )
            },
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            "Mandi Watch",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            "Live Millet Prices across Karnataka",
            fontSize = 13.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            items(filteredList) { item ->

                Card(
                    modifier = Modifier
                        .padding(
                            horizontal = 12.dp,
                            vertical = 6.dp
                        )
                        .fillMaxWidth(),

                    shape = RoundedCornerShape(16.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text(
                                item.name,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                item.desc,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        Column(
                            horizontalAlignment =
                                Alignment.End
                        ) {

                            Text(
                                "₹${item.price}/kg",
                                color = Color(0xFF3E5F1B),
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                "View Graph",
                                color = Color(0xFF3E5F1B),
                                fontSize = 12.sp,
                                modifier = Modifier.clickable {

                                    selectedMillet = item
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    selectedMillet?.let {

        PriceGraphDialog(

            millet = it,

            onDismiss = {

                selectedMillet = null
            }
        )
    }
}