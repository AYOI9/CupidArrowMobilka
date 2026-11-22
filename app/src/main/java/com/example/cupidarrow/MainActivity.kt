package com.example.cupidarrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.cupidarrow.navigation.AppNavigation
import com.example.cupidarrow.ui.theme.BackgroundWhite
import com.example.cupidarrow.ui.theme.CupidArrowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CupidArrowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundWhite
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
