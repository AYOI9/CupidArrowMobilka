package com.example.cupidarrow.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupidarrow.ui.components.OnboardingHeader
import com.example.cupidarrow.ui.components.PrimaryButton
import com.example.cupidarrow.ui.theme.BrandPink
import com.example.cupidarrow.ui.theme.PlaceholderGray
import com.example.cupidarrow.ui.theme.TextPrimary
import com.example.cupidarrow.ui.theme.TextSecondary

@Composable
fun NameScreen(
    initialName: String,
    onBackClick: () -> Unit,
    onContinue: (String) -> Unit
) {
    var name by remember { mutableStateOf(initialName.ifEmpty { "Евгений" }) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        OnboardingHeader(progress = 0.3f, onBackClick = onBackClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "What's Your Name?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Let's Get to Know Each Other",
                fontSize = 14.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(36.dp))

            Surface(
                shape = RoundedCornerShape(27.dp),
                border = BorderStroke(1.5.dp, BrandPink),
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    if (name.isEmpty()) {
                        Text(
                            text = "Enter your full name",
                            color = PlaceholderGray,
                            fontSize = 15.sp
                        )
                    }
                    BasicTextField(
                        value = name,
                        onValueChange = { name = it },
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary
                        ),
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = "Continue",
                onClick = { onContinue(name) },
                enabled = name.isNotBlank(),
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}
