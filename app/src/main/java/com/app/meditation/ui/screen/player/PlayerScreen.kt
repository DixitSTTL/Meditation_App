package com.app.meditation.ui.screen.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.app.meditation.R
import com.app.meditation.ui.screen.tuneList.DataTunes
import com.app.meditation.ui.theme.MyWhite
import com.app.meditation.ui.theme.White50
import com.app.meditation.ui.theme.White90
import kotlinx.coroutines.delay

@Composable
fun PlayerScreen(dataTunes: DataTunes, viewmodel: PlayerViewModel = hiltViewModel()) {

    var width by remember { mutableStateOf(0) }
    var part by remember { mutableStateOf(0) }
    var isLoaded by remember { mutableStateOf(false) }
    val painter = rememberImagePainter(data = dataTunes.image)

    val isPlaying = viewmodel.getIsPlaying().collectAsState().value
    val isPrepared = viewmodel.getIsisPrepared().collectAsState().value

    val offsetAnimation: Float by animateFloatAsState(

        if (isLoaded) 20f else 200f,
        animationSpec = tween(
            delayMillis = 1000,
            durationMillis = 1500
        )
    )
    LaunchedEffect(dataTunes) {
        isLoaded = false
        delay(1000)
        isLoaded = true

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = dataTunes.name!!, color = Color.White, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_bold)), fontSize = 22.sp
            )
        )
        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "By: Painting with Passion", color = White50, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_regular)), fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        LinearProgressIndicator(
            progress = 30f,
            color = MyWhite,
            trackColor = White50,
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(shape = RoundedCornerShape(50))
        )

        Spacer(modifier = Modifier.height(20.dp))
        Canvas(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            width = size.width.toInt()
            part = width / 10

            val pathEffect =
                PathEffect.dashPathEffect(floatArrayOf(offsetAnimation, 20f), 10f)


            drawLine(
                color = White90,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                pathEffect = pathEffect,
                strokeWidth = 15f,
                cap = StrokeCap.Round
            )


        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            Button(onClick = { /*TODO*/ }) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_shuffle),
                    contentDescription = "",
                    tint = Color.White
                )

            }
            Button(onClick = { /*TODO*/ }) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_backward),
                    contentDescription = "",
                    tint = Color.White
                )

            }

            AnimatedVisibility(visible = isPrepared) {
                Button(onClick = {

                    viewmodel.play_pause_Audio()
                }) {

                    Icon(
                        painter = painterResource(
                            id = if (isPlaying) {
                                R.drawable.ic_pause
                            } else {
                                R.drawable.ic_play
                            }
                        ),
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(45.dp)
                    )

                }
            }

            AnimatedVisibility(visible = !isPrepared) {
                CircularProgressIndicator(
                    modifier = Modifier,
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            }


            Button(onClick = { /*TODO*/ }) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_forward),
                    contentDescription = "",
                    tint = Color.White
                )

            }

            Button(onClick = { /*TODO*/ }) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_repeat),
                    contentDescription = "",
                    tint = Color.White
                )

            }


        }


    }


}
