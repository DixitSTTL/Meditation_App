package com.app.meditation.ui.activity.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.meditation.ui.activity.main.MainActivity
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeLSActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WelcomeApp(
                applicationContext = applicationContext,
                navigateToMain = {
                    startActivity(Intent(this@WelcomeLSActivity, MainActivity::class.java))
                    finish()

                },
                finishActivity = {
                    finish()
                })
        }

    }
}