package com.app.meditation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            val splashScreen = installSplashScreen()
//            splashScreen.setKeepOnScreenCondition { true }
//        }
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash);
        setContent {
            SetSplash()

        }
        lifecycleScope.launchWhenCreated {
            delay(3000)

            val intent = Intent(this@SplashActivity, WelcomeLSActivity::class.java)
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
                painter = painterResource(id = R.drawable.ic_logo_large),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(300.dp)
            )
        }


    }
}