package com.app.meditation.ui.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.navigation.NavHostController
import com.app.meditation.ui.screen.tuneList.DataTunes

class MainActions(navController: NavHostController,applicationContext: Context) {
    val showToast= {it: String ->
        Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()

    }
    val navigateBack: () -> Unit = {
        navController.popBackStack()

    }
    val navigatePlayer = { dataTune: DataTunes ->
        navController.navigate("${MainDestinations.PLAYER_ROUTE}/$dataTune")

    }
    val navigateToMeditation = {
        navController.navigate(MainDestinations.MEDITATION_ROUTE)

    }
    val navigateCardio = {
        var intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.data = Uri.parse("https://www.youtube.com/watch?v=QndOJ-cZ3n8");
        applicationContext.startActivity(intent)

    }
    val navigateMeditation = {
        var intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.data = Uri.parse("https://www.youtube.com/watch?v=SWDDUdevH7w");
        applicationContext.startActivity(intent)

    }
    val navigateToTools = {
        navController.navigate(MainDestinations.TOOLS_ROUTE)

    }
    val navigateToSleep = {
        navController.navigate(MainDestinations.SLEEP_ROUTE)

    }

}

object MainDestinations {
    const val PLAYER_ROUTE = "playerRoute"


    const val TOOLS_ROUTE = "toolsRoute"
    const val MEDITATION_ROUTE = "meditationRoute"
    const val SLEEP_ROUTE = "sleepRoute"


    const val DATA_TUNE_KEY = "data_tune_key"

}