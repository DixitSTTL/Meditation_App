package com.app.meditation.ui.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.app.meditation.ui.screen.MainDestinations
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val libraryViewModel: AppViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val initialScreen = intent.getStringExtra("initialScreen") ?: MainDestinations.WELCOME_ROUTE
        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
            App(applicationContext, widthSizeClass, {
                finish()
            }, libraryViewModel, initialScreen)
        }
    }
}
