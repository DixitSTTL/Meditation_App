package com.app.meditation.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.app.meditation.R
import com.app.meditation.ui.activity.main.MainActivity
import com.app.meditation.ui.screen.MainDestinations
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    @Inject
    lateinit var shared: UtilsSharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            SetSplash()

        }
        lifecycleScope.launchWhenCreated {
            delay(3000)

            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            if (shared.getBooleanDefault(resources.getResourceName(R.string.user_login), false))
                intent.putExtra("initialScreen", MainDestinations.DASHBOARD_ROUTE)
            else
                intent.putExtra("initialScreen", MainDestinations.WELCOME_ROUTE)


            startActivity(intent)
            finish()
        }
    }

    @Composable
    private fun SetSplash() {

        var isLoaded by remember { mutableStateOf(false) }

        val offsetAnimation: Dp by animateDpAsState(

            if (isLoaded) 20.dp else 0.dp,
            animationSpec = tween(
                durationMillis = 1600
            )
        )
        LaunchedEffect(key1 = "") {
            isLoaded = false
            delay(300)
            isLoaded = true

        }

        Box(modifier = Modifier.fillMaxWidth()) {

            Image(
                painter = painterResource(id = R.drawable.img_main_bg),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .blur(offsetAnimation),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.ic_logo_main),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(150.dp)
            )
        }

    }
}