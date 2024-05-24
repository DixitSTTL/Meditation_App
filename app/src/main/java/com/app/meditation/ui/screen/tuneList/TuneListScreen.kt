package com.app.meditation.ui.screen.tuneList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.app.meditation.ui.theme.White90

@Composable
fun TuneListScreen(dataTunes: (DataTunes) -> Unit, viewmodel: TuneViewModel = hiltViewModel()) {

    val list by viewmodel.musicList.collectAsState()
    val isLoading by viewmodel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
//        viewmodel.getTunes()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {

        item {

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(16.dp)),
                ) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.img_tune),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = "Relax Sounds ",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                                fontSize = 20.sp,
                                color = White90
                            )
                        )

                        Text(
                            text = "Sometimes the most productive\nthing you can do is relax.",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                                fontSize = 16.sp,
                                color = White90
                            )
                        )

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                        )

                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = White90
                            )
                        ) {

                            Row(verticalAlignment = Alignment.CenterVertically) {

                                Text(
                                    text = "play now",
                                    style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                                        color = Color.Black
                                    )
                                )

                                Icon(
                                    painter = painterResource(id = R.drawable.ic_play),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(6.dp, 0.dp)
                                        .size(18.dp),
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

            }
        }

        item {
            AnimatedVisibility(visible = isLoading, exit = fadeOut()) {

                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center),
                        color = Color.White
                    )
                }
            }

        }

        itemsIndexed(list) { i, item ->

            TuneItem(item, dataTunes)

        }

    }

}

@Composable
fun TuneItem(item: DataTunes, dataTunes: (DataTunes) -> Unit) {
    val painter = rememberImagePainter(data = item.image)

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(0.dp, 12.dp)
            .clickable {
                dataTunes(item)
            }) {
        Image(
            painter = painter,
            contentDescription = item.name,
            modifier = Modifier
                .size(80.dp)
                .clip(shape = RoundedCornerShape(20)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name.toString(),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                    fontSize = 16.sp,
                    color = Color.White
                )
            )

            Text(
                text = "${item.listener} monthly listeners",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                    fontSize = 12.sp,
                    color = Color.White
                )
            )

        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = "${item.duration} Min",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                color = Color.White
            )
        )

    }

}
