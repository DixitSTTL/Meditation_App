package com.app.meditation.ui

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.app.meditation.ui.theme.MeditationAppTheme

@Composable
fun WelcomeApp(applicationContext: Context, finishActivity: () -> Unit, navigateToMain: () -> Unit) {

    MeditationAppTheme {
        val navController = rememberNavController()

        Scaffold(
            contentColor = MaterialTheme.colorScheme.background,
        ) { innerPaddingModifier ->

            WelcomeNavGraph(
                finishActivity = finishActivity,
                navigateToMain = navigateToMain,
                navController = navController,
                modifier = Modifier.padding(innerPaddingModifier),
                applicationContext = applicationContext
            )

        }
    }

}