package com.example.nammasiridhanya.ui.theme.screens

import android.graphics.Color as AndroidColor
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceGraphDialog(

    millet: Millet,

    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss
    ) {

        Card(
            shape = RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(
                        millet.name,
                        style =
                            MaterialTheme.typography.titleLarge
                    )

                    Text(
                        "✕",
                        modifier = Modifier.clickable {

                            onDismiss()
                        }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text("Select District")

                Spacer(modifier = Modifier.height(8.dp))

                var expanded by remember {
                    mutableStateOf(false)
                }

                var selectedDistrict by remember {
                    mutableStateOf("Bengaluru Urban")
                }

                val districts = listOf(

                    "Bagalkote",
                    "Ballari",
                    "Belagavi",
                    "Bengaluru Urban",
                    "Bengaluru Rural",
                    "Bidar",
                    "Chamarajanagar",
                    "Chikkaballapur",
                    "Chikkamagaluru",
                    "Chitradurga",
                    "Dakshina Kannada",
                    "Davanagere",
                    "Dharwad",
                    "Gadag",
                    "Hassan",
                    "Haveri",
                    "Kalaburagi",
                    "Kodagu",
                    "Kolar",
                    "Koppal",
                    "Mandya",
                    "Mysuru",
                    "Raichur",
                    "Ramanagara",
                    "Shivamogga",
                    "Tumakuru",
                    "Udupi",
                    "Uttara Kannada",
                    "Vijayapura",
                    "Yadgir",
                    "Vijayanagara"
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {

                        expanded = !expanded
                    }
                ) {

                    OutlinedTextField(
                        value = selectedDistrict,

                        onValueChange = {},

                        readOnly = true,

                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),

                        label = {
                            Text("Select District")
                        },

                        trailingIcon = {

                            ExposedDropdownMenuDefaults
                                .TrailingIcon(
                                    expanded = expanded
                                )
                        }
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {

                            expanded = false
                        }
                    ) {

                        districts.forEach { district ->

                            DropdownMenuItem(
                                text = {
                                    Text(district)
                                },

                                onClick = {

                                    selectedDistrict =
                                        district

                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                val prices = when (selectedDistrict) {

                    "Bengaluru Urban" ->
                        listOf(
                            52f,54f,55f,54f,56f,55f,57f
                        )

                    "Mysuru" ->
                        listOf(
                            50f,51f,52f,53f,54f,53f,55f
                        )

                    "Mandya" ->
                        listOf(
                            48f,49f,50f,51f,52f,53f,54f
                        )

                    else ->
                        listOf(
                            45f,46f,47f,48f,49f,50f,51f
                        )
                }

                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),

                    factory = { context ->

                        val chart = LineChart(context)

                        val entries =
                            prices.mapIndexed {

                                    index,
                                    value ->

                                Entry(
                                    index.toFloat(),
                                    value
                                )
                            }

                        val dataSet = LineDataSet(
                            entries,
                            "Price ₹/kg"
                        ).apply {

                            color =
                                AndroidColor.parseColor(
                                    "#3E5F1B"
                                )

                            valueTextColor =
                                AndroidColor.BLACK

                            lineWidth = 3f

                            setCircleColor(
                                AndroidColor.parseColor(
                                    "#3E5F1B"
                                )
                            )

                            circleRadius = 5f

                            setDrawFilled(true)

                            fillColor =
                                AndroidColor.parseColor(
                                    "#A5D6A7"
                                )
                        }

                        chart.data =
                            LineData(dataSet)

                        chart.description.isEnabled =
                            false

                        chart.animateX(1000)

                        chart.invalidate()

                        chart
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Stock price over last 7 days"
                )
            }
        }
    }
}