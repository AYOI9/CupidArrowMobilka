package com.example.cupidarrow.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupidarrow.ui.components.OnboardingHeader
import com.example.cupidarrow.ui.components.PrimaryButton
import com.example.cupidarrow.ui.theme.BorderLight
import com.example.cupidarrow.ui.theme.BrandPink
import com.example.cupidarrow.ui.theme.TextPrimary
import com.example.cupidarrow.ui.theme.TextSecondary

data class InterestItem(val name: String, val icon: String)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InterestsScreen(
    initialInterests: Set<String>,
    onBackClick: () -> Unit,
    onContinue: (Set<String>) -> Unit
) {
    val allInterests = remember {
        listOf(
            InterestItem("Reading", "📖"),
            InterestItem("Photography", "📷"),
            InterestItem("Gaming", "🎮"),
            InterestItem("Music", "🎵"),
            InterestItem("Travel", "✈️"),
            InterestItem("Painting", "🎨"),
            InterestItem("Politics", "🏛️"),
            InterestItem("Charity", "🎗️"),
            InterestItem("Cooking", "🍳"),
            InterestItem("Pets", "🐾"),
            InterestItem("Sports", "⚽"),
            InterestItem("Fashion", "👗")
        )
    }

    var selectedInterests by remember {
        mutableStateOf(if (initialInterests.isNotEmpty()) initialInterests else setOf("Reading", "Music", "Painting"))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        OnboardingHeader(progress = 0.8f, onBackClick = onBackClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Select Up To 3 Interest",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tell us what piques your curiosity and passions",
                fontSize = 14.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(32.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                allInterests.forEach { item ->
                    val isSelected = selectedInterests.contains(item.name)
                    Surface(
                        shape = RoundedCornerShape(19.dp),
                        border = BorderStroke(1.dp, if (isSelected) BrandPink else BorderLight),
                        color = if (isSelected) BrandPink else Color.White,
                        modifier = Modifier
                            .height(38.dp)
                            .clickable {
                                val newSet = selectedInterests.toMutableSet()
                                if (isSelected) {
                                    newSet.remove(item.name)
                                } else if (newSet.size < 3) {
                                    newSet.add(item.name)
                                }
                                selectedInterests = newSet
                            }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Text(text = item.icon, fontSize = 14.sp)
                            Spacer(modifier = Modifier.height(0.dp))
                            Text(
                                text = "  ${item.name}",
                                fontSize = 14.sp,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                                color = if (isSelected) Color.White else TextPrimary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = "Continue",
                onClick = { onContinue(selectedInterests) },
                enabled = selectedInterests.isNotEmpty(),
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}
