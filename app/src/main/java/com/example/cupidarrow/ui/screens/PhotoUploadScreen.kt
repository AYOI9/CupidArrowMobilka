package com.example.cupidarrow.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cupidarrow.ui.components.OnboardingHeader
import com.example.cupidarrow.ui.components.PrimaryButton
import com.example.cupidarrow.ui.theme.BrandPink
import com.example.cupidarrow.ui.theme.SurfaceTrackPink
import com.example.cupidarrow.ui.theme.TextPrimary
import com.example.cupidarrow.ui.theme.TextSecondary

@Composable
fun PhotoUploadScreen(
    initialPhotos: List<String>,
    onBackClick: () -> Unit,
    onContinue: (List<String>) -> Unit
) {
    val photoList = remember {
        mutableStateListOf<String>().apply {
            if (initialPhotos.isNotEmpty()) addAll(initialPhotos)
        }
    }

    // Системный выбор фото из галереи устройства
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { photoList.add(it.toString()) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        OnboardingHeader(progress = 0.9f, onBackClick = onBackClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Upload Your Photo",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "We'd love to see you. Upload a photo for your dating journey.",
                fontSize = 14.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Сетка загрузки фотографий из 6 слотов как в макете
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Главный большой слот (2x2)
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = SurfaceTrackPink,
                    modifier = Modifier
                        .size(200.dp)
                        .clickable { photoPickerLauncher.launch("image/*") }
                ) {
                    val mainPhoto = photoList.firstOrNull()
                    if (mainPhoto != null) {
                        AsyncImage(
                            model = mainPhoto,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        EmptyAddSlot()
                    }
                }

                // 2 слота справа
                Column(
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    for (i in 1..2) {
                        SmallPhotoSlot(
                            photoUri = photoList.getOrNull(i),
                            onClick = { photoPickerLauncher.launch("image/*") }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 3 нижних слота
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                for (i in 3..5) {
                    SmallPhotoSlot(
                        photoUri = photoList.getOrNull(i),
                        onClick = { photoPickerLauncher.launch("image/*") }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = "Continue",
                onClick = { onContinue(photoList.toList()) },
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}

@Composable
private fun SmallPhotoSlot(
    photoUri: String?,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.5.dp, BrandPink.copy(alpha = 0.5f)),
        color = SurfaceTrackPink,
        modifier = Modifier
            .size(92.dp)
            .clickable { onClick() }
    ) {
        if (photoUri != null) {
            AsyncImage(
                model = photoUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            EmptyAddSlot()
        }
    }
}

@Composable
private fun EmptyAddSlot() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(BrandPink),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
