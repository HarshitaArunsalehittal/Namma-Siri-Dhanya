package com.example.nammasiridhanya.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

data class Recipe(
    val englishName: String,
    val kannadaName: String,
    val time: Int,
    val englishSteps: String,
    val kannadaSteps: String
)

@Composable
fun RecipeScreen(
    favoriteRecipes: MutableList<String>
) {

    var search by remember {
        mutableStateOf("")
    }

    val isKannada =
        Locale.getDefault().language == "kn"

    val recipes = listOf(

        Recipe(
            "Ragi Dosa",
            "ರಾಗಿ ದೋಸೆ",
            5,
            "1. Mix ragi flour\n2. Add water\n3. Heat pan\n4. Cook dosa",
            "1. ರಾಗಿ ಹಿಟ್ಟು ಮಿಶ್ರಣ ಮಾಡಿ\n2. ನೀರು ಸೇರಿಸಿ\n3. ತವಾ ಬಿಸಿ ಮಾಡಿ\n4. ದೋಸೆ ಬೇಯಿಸಿ"
        ),

        Recipe(
            "Navane Upma",
            "ನವಣೆ ಉಪ್ಪಿಟ್ಟು",
            10,
            "1. Roast millet\n2. Add vegetables\n3. Add water\n4. Cook well",
            "1. ನವಣೆ ಹುರಿಯಿರಿ\n2. ತರಕಾರಿ ಸೇರಿಸಿ\n3. ನೀರು ಸೇರಿಸಿ\n4. ಚೆನ್ನಾಗಿ ಬೇಯಿಸಿ"
        ),

        Recipe(
            "Foxtail Idli",
            "ನವಣೆ ಇಡ್ಲಿ",
            15,
            "1. Soak millet\n2. Grind batter\n3. Ferment\n4. Steam idli",
            "1. ನವಣೆ ನೆನೆಸಿರಿ\n2. ಹಿಟ್ಟು ರುಬ್ಬಿರಿ\n3. ಹುದುಗಲು ಬಿಡಿ\n4. ಇಡ್ಲಿ ಬೇಯಿಸಿ"
        ),

        Recipe(
            "Ragi Mudde",
            "ರಾಗಿ ಮುದ್ದೆ",
            20,
            "1. Boil water\n2. Add ragi flour\n3. Mix strongly\n4. Make balls",
            "1. ನೀರು ಕುದಿಸಿ\n2. ರಾಗಿ ಹಿಟ್ಟು ಸೇರಿಸಿ\n3. ಚೆನ್ನಾಗಿ ಕಲಸಿ\n4. ಉಂಡೆ ಮಾಡಿ"
        ),

        Recipe(
            "Millet Pongal",
            "ಸಿರಿಧಾನ್ಯ ಪೊಂಗಲ್",
            12,
            "1. Wash millet\n2. Add dal\n3. Pressure cook\n4. Add seasoning",
            "1. ಸಿರಿಧಾನ್ಯ ತೊಳೆಯಿರಿ\n2. ಬೇಳೆ ಸೇರಿಸಿ\n3. ಕುಕ್ಕರ್‌ನಲ್ಲಿ ಬೇಯಿಸಿ\n4. ಒಗ್ಗರಣೆ ಹಾಕಿ"
        )
    )

    val filtered = recipes.filter {

        it.englishName.contains(search, true) ||
                it.kannadaName.contains(search, true)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {

            OutlinedTextField(
                value = search,
                onValueChange = {
                    search = it
                },
                label = {
                    Text(
                        if (isKannada)
                            "ಪಾಕವಿಧಾನ ಹುಡುಕಿ"
                        else
                            "Search Recipes"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        items(filtered) { recipe ->

            var favorite by remember {
                mutableStateOf(
                    favoriteRecipes.contains(
                        if (isKannada)
                            recipe.kannadaName
                        else
                            recipe.englishName
                    )
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text =
                                if (isKannada)
                                    recipe.kannadaName
                                else
                                    recipe.englishName,

                            fontSize = 22.sp
                        )

                        IconButton(
                            onClick = {

                                favorite = !favorite

                                val recipeName =
                                    if (isKannada)
                                        recipe.kannadaName
                                    else
                                        recipe.englishName

                                if (favorite) {

                                    if (!favoriteRecipes.contains(recipeName)) {

                                        favoriteRecipes.add(recipeName)
                                    }

                                } else {

                                    favoriteRecipes.remove(recipeName)
                                }
                            }
                        ) {

                            Icon(
                                imageVector =
                                    if (favorite)
                                        Icons.Default.Favorite
                                    else
                                        Icons.Default.FavoriteBorder,

                                contentDescription = null,

                                tint =
                                    if (favorite)
                                        Color.Red
                                    else
                                        Color.Gray
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text =
                            if (isKannada)
                                "ಅಡುಗೆ ಸಮಯ: ${recipe.time} ನಿಮಿಷ"
                            else
                                "Cooking Time: ${recipe.time} mins"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text =
                            if (isKannada)
                                recipe.kannadaSteps
                            else
                                recipe.englishSteps
                    )
                }
            }
        }
    }
}