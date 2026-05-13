package com.example.nammasiridhanya.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

data class MilletHealth(
    val englishName: String,
    val kannadaName: String,
    val protein: Int,
    val fiber: Int,
    val calcium: Int,
    val benefitEnglish: String,
    val benefitKannada: String
)

@Composable
fun HealthScreen() {

    val isKannada =
        Locale.getDefault().language == "kn"

    var selectedMillet by remember {
        mutableStateOf<MilletHealth?>(null)
    }

    val millets = listOf(

        MilletHealth("Ragi","ರಾಗಿ",7,11,344,"Rich in calcium","ಕ್ಯಾಲ್ಸಿಯಂ ಹೆಚ್ಚು"),

        MilletHealth("Foxtail Millet","ನವಣೆ",12,8,31,"Good for diabetes","ಮಧುಮೇಹಕ್ಕೆ ಉತ್ತಮ"),

        MilletHealth("Sajje","ಸಜ್ಜೆ",11,9,27,"Improves digestion","ಜೀರ್ಣಕ್ರಿಯೆ ಸುಧಾರಣೆ"),

        MilletHealth("Kodo Millet","ಹಾರಕ",8,10,35,"Helps weight loss","ತೂಕ ಇಳಿಕೆಗೆ ಸಹಾಯ"),

        MilletHealth("Barnyard Millet","ಊದಲು",10,12,20,"Good for heart","ಹೃದಯಕ್ಕೆ ಉತ್ತಮ"),

        MilletHealth("Little Millet","ಸಾಮೆ",9,8,18,"Boosts immunity","ರೋಗ ನಿರೋಧಕ ಶಕ್ತಿ"),

        MilletHealth("Proso Millet","ಬರಗು",11,7,14,"Rich in protein","ಪ್ರೋಟೀನ್ ಹೆಚ್ಚು"),

        MilletHealth("Pearl Millet","ಬಜ್ರಾ",12,10,42,"Good for energy","ಶಕ್ತಿ ಹೆಚ್ಚಿಸುತ್ತದೆ"),

        MilletHealth("Finger Millet","ರಾಗಿ",7,11,344,"Strong bones","ಎಲುಬು ಬಲ"),

        MilletHealth("Brown Top Millet","ಕೊರಲೆ",9,9,25,"Improves metabolism","ಮೆಟಾಬಾಲಿಸಂ ಉತ್ತಮ"),

        MilletHealth("Sorghum","ಜೋಳ",10,8,30,"Good for gut","ಆಂತರ್ಯ ಆರೋಗ್ಯ"),

        MilletHealth("Jowar","ಜೋಳ",11,9,28,"Improves stamina","ಶಕ್ತಿ ಹೆಚ್ಚಿಸುತ್ತದೆ"),

        MilletHealth("Bajra","ಸಜ್ಜೆ",12,8,32,"Controls cholesterol","ಕೊಲೆಸ್ಟ್ರಾಲ್ ನಿಯಂತ್ರಣ"),

        MilletHealth("Korale","ಕೊರಲೆ",9,7,20,"Good for skin","ಚರ್ಮಕ್ಕೆ ಉತ್ತಮ"),

        MilletHealth("Samai","ಸಾಮೆ",8,6,16,"Improves immunity","ರೋಗ ನಿರೋಧಕ ಶಕ್ತಿ"),

        MilletHealth("Kutki","ಕುಟ್ಕಿ",9,8,18,"Supports heart health","ಹೃದಯ ಆರೋಗ್ಯ"),

        MilletHealth("Arka Millet","ಅರ್ಕಾ",10,9,26,"Rich in minerals","ಖನಿಜಗಳು ಹೆಚ್ಚು"),

        MilletHealth("Organic Ragi","ಆರ್ಗ್ಯಾನಿಕ್ ರಾಗಿ",8,12,350,"Healthy organic millet","ಆರೋಗ್ಯಕರ ಸಿರಿಧಾನ್ಯ"),

        MilletHealth("Organic Navane","ಆರ್ಗ್ಯಾನಿಕ್ ನವಣೆ",11,8,29,"Natural healthy food","ನೈಸರ್ಗಿಕ ಆಹಾರ"),

        MilletHealth("Organic Sajje","ಆರ್ಗ್ಯಾನಿಕ್ ಸಜ್ಜೆ",12,10,31,"Improves energy","ಶಕ್ತಿ ಹೆಚ್ಚಿಸುತ್ತದೆ"),

        MilletHealth("Red Jowar","ಕೆಂಪು ಜೋಳ",10,7,24,"Rich in iron","ಐರನ್ ಹೆಚ್ಚು"),

        MilletHealth("White Jowar","ಬಿಳಿ ಜೋಳ",9,7,22,"Easy digestion","ಸುಲಭ ಜೀರ್ಣ"),

        MilletHealth("Black Bajra","ಕಪ್ಪು ಸಜ್ಜೆ",13,10,33,"Boosts immunity","ರೋಗ ನಿರೋಧಕ ಶಕ್ತಿ"),

        MilletHealth("Golden Foxtail","ಗೋಲ್ಡನ್ ನವಣೆ",12,9,28,"Good for diabetic diet","ಮಧುಮೇಹ ಆಹಾರಕ್ಕೆ ಉತ್ತಮ"),

        MilletHealth("Tiny Millet","ಚಿಕ್ಕ ಸಿರಿಧಾನ್ಯ",8,8,15,"Low calorie food","ಕಡಿಮೆ ಕ್ಯಾಲರಿ"),

        MilletHealth("Green Millet","ಹಸಿರು ಸಿರಿಧಾನ್ಯ",9,9,18,"Good for detox","ದೇಹ ಶುದ್ಧೀಕರಣ"),

        MilletHealth("Dryland Millet","ಒಣಭೂಮಿ ಸಿರಿಧಾನ್ಯ",10,10,20,"Supports farming health","ಆರೋಗ್ಯಕರ ಆಹಾರ"),

        MilletHealth("Hill Millet","ಗುಡ್ಡ ಸಿರಿಧಾನ್ಯ",11,11,24,"Rich in fiber","ಫೈಬರ್ ಹೆಚ್ಚು"),

        MilletHealth("Forest Millet","ಕಾಡು ಸಿರಿಧಾನ್ಯ",10,9,21,"Natural nutrition","ನೈಸರ್ಗಿಕ ಪೌಷ್ಟಿಕಾಂಶ"),

        MilletHealth("Premium Ragi","ಪ್ರೀಮಿಯಂ ರಾಗಿ",8,12,348,"Strong bones","ಎಲುಬು ಬಲ")
    )

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {

        item {

            Text(
                text =
                    if (isKannada)
                        "ಸಿರಿಧಾನ್ಯ ಆರೋಗ್ಯ ಮಾಹಿತಿ"
                    else
                        "Millet Health Information",

                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        item {

            selectedMillet?.let { millet ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text =
                                if (isKannada)
                                    millet.kannadaName
                                else
                                    millet.englishName,

                            fontSize = 26.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text =
                                if (isKannada)
                                    "ಪ್ರೋಟೀನ್: ${millet.protein}g"
                                else
                                    "Protein: ${millet.protein}g"
                        )

                        LinearProgressIndicator(
                            progress = millet.protein / 20f,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text =
                                if (isKannada)
                                    "ಫೈಬರ್: ${millet.fiber}g"
                                else
                                    "Fiber: ${millet.fiber}g"
                        )

                        LinearProgressIndicator(
                            progress = millet.fiber / 20f,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text =
                                if (isKannada)
                                    "ಕ್ಯಾಲ್ಸಿಯಂ: ${millet.calcium}mg"
                                else
                                    "Calcium: ${millet.calcium}mg"
                        )

                        LinearProgressIndicator(
                            progress = millet.calcium / 400f,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text =
                                if (isKannada)
                                    millet.benefitKannada
                                else
                                    millet.benefitEnglish
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Card {

                            Text(
                                text =
                                    if (isKannada)
                                        "AI ಸಲಹೆ: ಈ ಸಿರಿಧಾನ್ಯ ಆರೋಗ್ಯಕ್ಕೆ ಉತ್ತಮ"
                                    else
                                        "AI Advice: This millet is very healthy",

                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        items(millets) { millet ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .clickable {

                        selectedMillet = millet
                    }
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text =
                            if (isKannada)
                                millet.kannadaName
                            else
                                millet.englishName,

                        fontSize = 22.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text =
                            if (isKannada)
                                millet.benefitKannada
                            else
                                millet.benefitEnglish
                    )
                }
            }
        }
    }
}