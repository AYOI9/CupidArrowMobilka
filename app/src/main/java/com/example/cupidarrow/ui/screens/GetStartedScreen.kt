package com.example.cupidarrow.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupidarrow.R
import com.example.cupidarrow.ui.theme.BrandPink
import com.example.cupidarrow.ui.theme.TextPrimary
import com.example.cupidarrow.ui.theme.TextSecondary

@Composable
fun GetStartedScreen(
    onLoginWithPhone: () -> Unit,
    onSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Верхний логотип
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

        Spacer(modifier = Modifier.height(36.dp))

        // Композиция из 3 аватаров в стиле макета
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentAlignment = Alignment.Center
        ) {
            // Центральный аватар
            Surface(
                modifier = Modifier
                    .size(120.dp)
                    .offset(y = (-15).dp),
                shape = CircleShape,
                border = BorderStroke(4.dp, Color.White),
                shadowElevation = 8.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.photo_karina),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            // Правый аватар
            Surface(
                modifier = Modifier
                    .size(80.dp)
                    .offset(x = 90.dp, y = 20.dp),
                shape = CircleShape,
                border = BorderStroke(3.dp, Color.White),
                shadowElevation = 6.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.photo_jessica),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            // Левый нижний аватар
            Surface(
                modifier = Modifier
                    .size(60.dp)
                    .offset(x = (-85).dp, y = 40.dp),
                shape = CircleShape,
                border = BorderStroke(2.dp, Color.White),
                shadowElevation = 4.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.photo_sofia),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Discover Love Where Your Story Begins.",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = TextPrimary,
            lineHeight = 30.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Join us to discover your ideal partner and ignite the sparks of romance in your journey.",
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            color = TextSecondary,
            lineHeight = 19.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        // Кнопка входа по телефону
        Button(
            onClick = onLoginWithPhone,
            shape = RoundedCornerShape(27.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BrandPink),
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        tint = BrandPink,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Login with Phone",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account? ",
                fontSize = 14.sp,
                color = TextSecondary
            )
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
