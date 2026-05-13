package com.example.nammasiridhanya.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.entryOf
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun MilletDetailScreen(
    milletName: String
) {

    val chartEntries = listOf(

        entryOf(0, 42f),
        entryOf(1, 44f),
        entryOf(2, 43f),
        entryOf(3, 46f),
        entryOf(4, 45f),
        entryOf(5, 47f),
        entryOf(6, 49f)
    )

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            text = milletName,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "7 Day Market Trend"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Chart(
            chart = lineChart(),
            model = entryModelOf(chartEntries)
        )
    }
}