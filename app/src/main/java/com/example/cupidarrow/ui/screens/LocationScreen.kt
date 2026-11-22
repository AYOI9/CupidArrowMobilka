package com.example.cupidarrow.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupidarrow.ui.components.PrimaryButton
import com.example.cupidarrow.ui.theme.BrandPink
import com.example.cupidarrow.ui.theme.SurfaceTrackPink
import com.example.cupidarrow.ui.theme.TextPrimary
import com.example.cupidarrow.ui.theme.TextSecondary

@Composable
fun LocationScreen(
    onLocationGranted: (Boolean) -> Unit
) {
    // Системный запрос разрешения на геолокацию
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        onLocationGranted(isGranted)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        // Иллюстрация пина геолокации в круге
        Box(
            modifier = Modifier
                .size(140.dp)
                .background(SurfaceTrackPink, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = BrandPink,
                modifier = Modifier.size(72.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Enable Your Location",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Choose your location to start find people around you",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = TextSecondary,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "Allow Location Access",
            onClick = {
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Enter Location Manually",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = BrandPink,
            modifier = Modifier
                .clickable { onLocationGranted(false) }
                .padding(bottom = 32.dp)
        )
    }
}
