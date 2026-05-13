
package com.example.nammasiridhanya.ui.theme.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AiAssistantScreen() {

    var question by remember {
        mutableStateOf("")
    }

    var answer by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        OutlinedTextField(
            value = question,
            onValueChange = {
                question = it
            },
            label = {
                Text("Ask AI")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                answer =
                    "Ragi is very good for calcium and diabetes control."

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Ask")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = answer)
    }
}