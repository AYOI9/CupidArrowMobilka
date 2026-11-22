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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupidarrow.ui.components.OnboardingHeader
import com.example.cupidarrow.ui.components.PrimaryButton
import com.example.cupidarrow.ui.theme.BorderLight
import com.example.cupidarrow.ui.theme.BrandPink
import com.example.cupidarrow.ui.theme.TextPrimary
import com.example.cupidarrow.ui.theme.TextSecondary
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(
    phone: String,
    onBackClick: () -> Unit,
    onVerify: () -> Unit
) {
    var otpValue by remember { mutableStateOf("51") }
    var secondsLeft by remember { mutableIntStateOf(30) }

    LaunchedEffect(secondsLeft) {
        if (secondsLeft > 0) {
            delay(1000)
            secondsLeft--
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        OnboardingHeader(progress = 0.2f, onBackClick = onBackClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Verification Code",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Please enter code we just send to ",
                    fontSize = 13.sp,
                    color = TextSecondary
                )
                Text(
                    text = phone.ifEmpty { "+7 999 123 45 67" },
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 4 круглых ячейки с прозрачным общим TextField поверх
            Box(contentAlignment = Alignment.Center) {
                BasicTextField(
                    value = otpValue,
                    onValueChange = { if (it.length <= 4 && it.all { char -> char.isDigit() }) otpValue = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    modifier = Modifier.size(260.dp, 60.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    for (i in 0 until 4) {
                        val digit = otpValue.getOrNull(i)?.toString() ?: "-"
                        val isFilled = i < otpValue.length
                        Surface(
                            shape = CircleShape,
                            border = BorderStroke(
                                1.5.dp,
                                if (isFilled) BrandPink else BorderLight
                            ),
                            color = Color.White,
                            modifier = Modifier.size(56.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = digit,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isFilled) TextPrimary else TextSecondary
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Didn't receive OTP? ",
                    fontSize = 14.sp,
                    color = TextSecondary
                )
                if (secondsLeft > 0) {
                    Text(
                        text = "Resend in ${secondsLeft}s",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BrandPink
                    )
                } else {
                    Text(
                        text = "Resend Code",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandPink,
                        modifier = Modifier.clickable { secondsLeft = 30 }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = "Verify",
                onClick = onVerify,
                enabled = otpValue.length >= 2,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}
