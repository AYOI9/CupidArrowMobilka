package com.example.cupidarrow.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.example.cupidarrow.model.LookingForOption
import com.example.cupidarrow.ui.components.OnboardingHeader
import com.example.cupidarrow.ui.components.PrimaryButton
import com.example.cupidarrow.ui.theme.BorderLight
import com.example.cupidarrow.ui.theme.BrandPink
import com.example.cupidarrow.ui.theme.TextPrimary
import com.example.cupidarrow.ui.theme.TextSecondary

@Composable
fun LookingForScreen(
    initialOption: LookingForOption?,
    onBackClick: () -> Unit,
    onContinue: (LookingForOption) -> Unit
) {
    var selectedOption by remember { mutableStateOf(initialOption ?: LookingForOption.CASUAL) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        OnboardingHeader(progress = 0.7f, onBackClick = onBackClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "I Am Looking For...",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Provide us with further insights into your preferences",
                fontSize = 14.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(36.dp))

            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                LookingForOption.values().forEach { option ->
                    val isSelected = option == selectedOption
                    Surface(
                        shape = RoundedCornerShape(27.dp),
                        border = BorderStroke(
                            1.5.dp,
                            if (isSelected) BrandPink else BorderLight
                        ),
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
                            .clickable { selectedOption = option }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp)
                        ) {
                            Text(
                                text = option.label,
                                fontSize = 15.sp,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                                color = TextPrimary,
                                modifier = Modifier.weight(1f)
                            )

                            // Кастомный радио-индикатор
                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .background(Color.White, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Surface(
                                    shape = CircleShape,
                                    border = BorderStroke(
                                        2.dp,
                                        if (isSelected) BrandPink else BorderLight
                                    ),
                                    color = Color.White,
                                    modifier = Modifier.size(20.dp)
                                ) {
                                    if (isSelected) {
                                        Box(
                                            modifier = Modifier
                                                .padding(4.dp)
                                                .background(BrandPink, CircleShape)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = "Continue",
                onClick = { onContinue(selectedOption) },
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}
