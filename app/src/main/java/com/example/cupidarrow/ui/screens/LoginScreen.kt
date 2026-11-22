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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupidarrow.R
import com.example.cupidarrow.ui.components.PrimaryButton
import com.example.cupidarrow.ui.components.SocialButton
import com.example.cupidarrow.ui.theme.BorderLight
import com.example.cupidarrow.ui.theme.BrandPink
import com.example.cupidarrow.ui.theme.DividerGray
import com.example.cupidarrow.ui.theme.PlaceholderGray
import com.example.cupidarrow.ui.theme.SocialBlue
import com.example.cupidarrow.ui.theme.TextPrimary
import com.example.cupidarrow.ui.theme.TextSecondary

@Composable
fun LoginScreen(
    onContinue: (String) -> Unit,
    onSignUp: () -> Unit
) {
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_cupid_arrow),
                contentDescription = null,
                tint = BrandPink,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "CupidArrow",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = BrandPink
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Let's start with your number",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Поле ввода телефона в виде капсулы
        Surface(
            shape = RoundedCornerShape(27.dp),
            border = BorderStroke(1.dp, BorderLight),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(text = "🇷🇺", fontSize = 18.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "+7",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = TextSecondary,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))
                Divider(
                    modifier = Modifier
                        .height(24.dp)
                        .width(1.dp),
                    color = DividerGray
                )
                Spacer(modifier = Modifier.width(12.dp))

                Box(modifier = Modifier.weight(1f)) {
                    if (phoneNumber.isEmpty()) {
                        Text(
                            text = "999 123 45 67",
                            color = PlaceholderGray,
                            fontSize = 15.sp
                        )
                    }
                    BasicTextField(
                        value = phoneNumber,
                        onValueChange = { if (it.length <= 10) phoneNumber = it },
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = "Continue",
            onClick = { onContinue("+7 $phoneNumber") },
            enabled = phoneNumber.isNotEmpty()
        )

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(modifier = Modifier.weight(1f), color = DividerGray)
            Text(
                text = "OR",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = PlaceholderGray,
                modifier = Modifier.padding(horizontal = 14.dp)
            )
            Divider(modifier = Modifier.weight(1f), color = DividerGray)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Facebook кнопка
        SocialButton(
            text = "Login with Facebook",
            icon = {
                Text(text = "f", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = SocialBlue)
            },
            onClick = { onContinue("+7 999 123 45 67") }
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Google кнопка
        SocialButton(
            text = "Login with Google",
            icon = {
                Text(text = "G", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = BrandPink)
            },
            onClick = { onContinue("+7 999 123 45 67") }
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don't have an account? ", fontSize = 14.sp, color = TextSecondary)
            Text(
                text = "Sign Up",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = BrandPink,
                modifier = Modifier.clickable { onSignUp() }
            )
        }
    }
}
