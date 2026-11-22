package com.example.cupidarrow.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupidarrow.R
import com.example.cupidarrow.model.FeedCard
import com.example.cupidarrow.ui.theme.BrandAccentGold
import com.example.cupidarrow.ui.theme.BrandPink
import com.example.cupidarrow.ui.theme.PlaceholderGray
import com.example.cupidarrow.ui.theme.SurfaceTrackPink
import com.example.cupidarrow.ui.theme.TextPrimary
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

data class StoryItem(val name: String, val imageResId: Int, val isUser: Boolean = false)

@Composable
fun MainFeedScreen(
    cards: List<FeedCard>,
    onCardSwiped: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val stories = remember {
        listOf(
            StoryItem("Add Story", R.drawable.photo_karina, isUser = true),
            StoryItem("Джессика", R.drawable.photo_jessica),
            StoryItem("София", R.drawable.photo_sofia),
            StoryItem("Михаил", R.drawable.photo_mikhail),
            StoryItem("Дарья", R.drawable.photo_daria),
            StoryItem("Артём", R.drawable.photo_artem)
        )
    }

    val currentCard = cards.firstOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Верхний бар
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Text(text = "▦", fontSize = 22.sp, color = BrandPink, fontWeight = FontWeight.Bold)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cupid_arrow),
                    contentDescription = null,
                    tint = BrandPink,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "CupidArrow",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandPink
                )
            }

            IconButton(onClick = {}) {
                Text(text = "⚡", fontSize = 22.sp, color = BrandAccentGold)
            }
        }

        // Карусель историй (Stories)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp)
        ) {
            items(stories) { story ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(
                            2.dp,
                            if (story.isUser) BrandPink else SurfaceTrackPink
                        ),
                        modifier = Modifier.size(56.dp)
                    ) {
                        Image(
                            painter = painterResource(id = story.imageResId),
                            contentDescription = story.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = story.name,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary
                    )
                }
            }
        }

        // Основная область стека свайп-карточек
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (currentCard != null) {
                SwipeableProfileCard(
                    card = currentCard,
                    onSwiped = onCardSwiped
                )
            } else {
                Text(
                    text = "Вы посмотрели все анкеты поблизости!",
                    fontSize = 16.sp,
                    color = PlaceholderGray,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Кнопки управления (Дислайк, Лайк, Суперлайк)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка Дислайк (Крестик)
            Surface(
                shape = CircleShape,
                shadowElevation = 6.dp,
                color = Color.White,
                modifier = Modifier.size(52.dp)
            ) {
                IconButton(onClick = onCardSwiped) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Pass",
                        tint = BrandPink,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Кнопка Лайк (Большое сердечко)
            Surface(
                shape = CircleShape,
                shadowElevation = 8.dp,
                color = BrandPink,
                modifier = Modifier.size(64.dp)
            ) {
                IconButton(onClick = onCardSwiped) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Like",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            // Кнопка Суперлайк (Звездочка)
            Surface(
                shape = CircleShape,
                shadowElevation = 6.dp,
                color = Color.White,
                modifier = Modifier.size(52.dp)
            ) {
                IconButton(onClick = onCardSwiped) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Superlike",
                        tint = BrandAccentGold,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }

        // Нижняя панель навигации (4 вкладки)
        NavigationBar(
            containerColor = Color.White,
            tonalElevation = 4.dp,
            modifier = Modifier.height(58.dp)
        ) {
            val tabs = listOf("🗂️", "❤️", "💬", "👤")
            tabs.forEachIndexed { index, icon ->
                NavigationBarItem(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    icon = { Text(text = icon, fontSize = 20.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = SurfaceTrackPink
                    )
                )
            }
        }
    }
}

@Composable
private fun SwipeableProfileCard(
    card: FeedCard,
    onSwiped: () -> Unit
) {
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    // Динамический угол наклона при перетаскивании (эффект физики Tinder)
    val rotation = (offsetX.value / 60f).coerceIn(-15f, 15f)

    Surface(
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 8.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 4.dp)
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .rotate(rotation)
            .pointerInput(card.id) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        coroutineScope.launch {
                            offsetX.snapTo(offsetX.value + dragAmount.x)
                            offsetY.snapTo(offsetY.value + dragAmount.y)
                        }
                    },
                    onDragEnd = {
                        coroutineScope.launch {
                            if (offsetX.value > 300f || offsetX.value < -300f) {
                                val targetX = if (offsetX.value > 0) 1000f else -1000f
                                offsetX.animateTo(targetX, tween(200))
                                onSwiped()
                            } else {
                                offsetX.animateTo(0f, tween(200))
                                offsetY.animateTo(0f, tween(200))
                            }
                        }
                    }
                )
            }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = card.imageResId),
                contentDescription = card.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Градиентное затемнение снизу для читаемости текста
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {
                Text(
                    text = "${card.name}, ${card.age}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = card.location,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White.copy(alpha = 0.85f),
                    letterSpacing = 1.sp
                )
            }
        }
    }
}
