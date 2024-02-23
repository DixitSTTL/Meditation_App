package com.app.meditation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.meditation.ui.WelcomeApp

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