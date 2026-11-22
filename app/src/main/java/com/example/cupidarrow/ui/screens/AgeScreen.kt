package com.example.cupidarrow.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupidarrow.ui.components.OnboardingHeader
import com.example.cupidarrow.ui.components.PrimaryButton
import com.example.cupidarrow.ui.theme.BrandPink
import com.example.cupidarrow.ui.theme.TextPrimary
import com.example.cupidarrow.ui.theme.TextSecondary
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AgeScreen(
    initialAge: Int,
    onBackClick: () -> Unit,
    onContinue: (Int) -> Unit
) {
    val ages = remember { (18..70).toList() }
    val initialIndex = remember { (initialAge - 18).coerceIn(0, ages.lastIndex) }
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)
    val coroutineScope = rememberCoroutineScope()

    // Индекс центрального выбранного элемента в колесе прокрутки
    val selectedIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            if (visibleItems.isEmpty()) initialIndex
            else {
                val center = layoutInfo.viewportStartOffset + (layoutInfo.viewportEndOffset - layoutInfo.viewportStartOffset) / 2
                visibleItems.minByOrNull { abs((it.offset + it.size / 2) - center) }?.index ?: initialIndex
            }
        }
    }

    val selectedAge = ages.getOrElse(selectedIndex) { 32 }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        OnboardingHeader(progress = 0.5f, onBackClick = onBackClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "How Old Are You?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Please provide your age in years",
                fontSize = 14.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Вертикальный барабан с фиксацией (Wheel Picker)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp),
                contentAlignment = Alignment.Center
            ) {
                // Розовые горизонтальные разделители выбранного возраста
                Column(
                    modifier = Modifier
                        .width(140.dp)
                        .height(60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Divider(color = BrandPink, thickness = 2.dp)
                    Spacer(modifier = Modifier.weight(1f))
                    Divider(color = BrandPink, thickness = 2.dp)
                }

                LazyColumn(
                    state = listState,
                    flingBehavior = rememberSnapFlingBehavior(lazyListState = listState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                ) {
                    items(ages.size) { index ->
                        val age = ages[index]
                        val isSelected = index == selectedIndex
                        val diff = abs(index - selectedIndex)

                        val fontSize = when (diff) {
                            0 -> 36.sp
                            1 -> 24.sp
                            2 -> 20.sp
                            else -> 16.sp
                        }
                        val fontColor = when (diff) {
                            0 -> BrandPink
                            1 -> TextPrimary.copy(alpha = 0.6f)
                            2 -> TextSecondary.copy(alpha = 0.4f)
                            else -> TextSecondary.copy(alpha = 0.2f)
                        }

                        Box(
                            modifier = Modifier
                                .height(52.dp)
                                .clickable {
                                    coroutineScope.launch {
                                        listState.animateScrollToItem((index - 2).coerceAtLeast(0))
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = age.toString(),
                                fontSize = fontSize,
                                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
                                color = fontColor
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = "Continue",
                onClick = { onContinue(selectedAge) },
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}
