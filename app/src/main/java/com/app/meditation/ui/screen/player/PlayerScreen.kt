package com.app.meditation.ui.screen.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.app.meditation.R
import com.app.meditation.ui.screen.tuneList.DataTunes
import com.app.meditation.ui.theme.GreenDark
import com.app.meditation.ui.theme.GreenLight
import com.app.meditation.ui.theme.White50
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun PlayerScreen(dataTunes: DataTunes, viewmodel: PlayerViewModel = hiltViewModel()) {

    val state = viewmodel.getState().collectAsState().value

    val offsetAnimation: Float by animateFloatAsState(

        if (state.isPrepared) 100f else 30f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessVeryLow
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        AsyncImage(
            model = dataTunes.image,
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

        DashedProgressBar(
            progress = state.currentProgress,
            color = Color.Blue,
            backgroundColor = Color.LightGray,
            dashWidth = 10f,
            dashGap = 5f,
            strokeWidth = 4f,
            offsetAnimation,
            viewmodel = viewmodel,
        )

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

            AnimatedVisibility(visible = state.isPrepared) {
                Button(onClick = {

                    viewmodel.playPauseAudio()
                }) {

                    Icon(
                        painter = painterResource(
                            id = if (state.isPlaying) {
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

            AnimatedVisibility(visible = !state.isPrepared) {
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

            Button(
                onClick = {
                    viewmodel.setLooping(!state.isLooping)
                }, shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(
                    containerColor = if (state.isLooping) Color.White else Color.Transparent
                )
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_repeat),
                    contentDescription = "",
                    tint = if (!state.isLooping) Color.White else GreenDark
                )

            }


        }


    }


}


@Composable
fun DashedProgressBar(
    progress: Float,
    color: Color,
    backgroundColor: Color = Color.LightGray,
    dashWidth: Float = 60f,
    dashGap: Float = 30f,
    strokeWidth: Float = 15f,
    offsetAnimation: Float,
    viewmodel: PlayerViewModel,
) {
    /*
 */
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .pointerInput("Canvas") {
                val size = this.size
                val m = MutableStateFlow(0f)
                detectDragGestures(
                    {},
                    {
                        viewmodel.seekToPosition(m.value)
                    },
                    {},
                    { t, d ->

                        var X = t.position.x
                        if (X > size.width) {
                            X = size.width.toFloat()
                        } else if (X < 0) {
                            X = 0f
                        }
                        val per = X / size.width
                        m.value = per
                        viewmodel.updateProgress(per)
                    }

                )

            }
    ) {
        val totalWidth = size.width
        val halfWidth = size.width / -2
        val dashWidthWithGap = dashWidth + dashGap
        val progressWidth = totalWidth * progress

        // Draw the background dashed line
        val paintBackground = Paint().apply {
            this.color = backgroundColor
            this.strokeWidth = strokeWidth
            this.strokeCap = StrokeCap.Round
            this.pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(dashWidth, dashGap), 0f
            )
        }
        val pathEffect =
            PathEffect.dashPathEffect(floatArrayOf(offsetAnimation, 40f), halfWidth)
        drawLine(
            color = Color.White,
            start = Offset(0f, size.height / 2),
            end = Offset(totalWidth, size.height / 2),
            strokeWidth = 20f,
            pathEffect = pathEffect,
            cap = StrokeCap.Round
        )

        // Draw the progress dashed line
        val paintProgress = Paint().apply {
            this.color = color
            this.strokeWidth = strokeWidth
            this.strokeCap = StrokeCap.Round
            this.pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(dashWidth, dashGap), 0f
            )
        }

        drawLine(
            color = GreenLight,
            start = Offset(0f, size.height / 2),
            end = Offset(progressWidth, size.height / 2),
            strokeWidth = 20f,
            pathEffect = pathEffect,
            cap = StrokeCap.Round

        )
    }
}