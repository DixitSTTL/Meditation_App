package com.app.meditation.ui.screen.sleep

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.meditation.R
import com.app.meditation.ui.Constants.sleepList

@Composable
fun SleepScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Sleep Session", color = Color.White, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_bold)), fontSize = 26.sp
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(horizontalArrangement = Arrangement.Absolute.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            items(sleepList) {

                SleepItem(it)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Bedtime", color = Color.White, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_bold)), fontSize = 26.sp
            ))
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = colorResource(id = R.color.color_3E8469))

        ) {}

    }


}

@Composable
fun SleepItem(dataTools: DataSleep) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = colorResource(id = dataTools.bgColor))
            .padding(20.dp)


    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Icon(
            painter = painterResource(id = dataTools.icon),
            contentDescription = "",
            tint = Color.White
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = dataTools.value, color = Color.White, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)), fontSize = 22.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = dataTools.title, color = Color.White, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_regular)), fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(30.dp))




    }


}

@Preview
@Composable
fun PreView() {
    val data =
        DataSleep(
            title = "Deep",
            value = "1h 10m",
            bgColor = R.color.color_498A78,
            icon = R.drawable.ic_deep
        )
    SleepItem(data)
}