package com.app.meditation.ui.screen.dashbord

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.meditation.R
import com.app.meditation.ui.theme.GreenDark
import com.app.meditation.ui.theme.MyWhite
import com.app.meditation.ui.theme.White50
import com.app.meditation.ui.theme.White90

@Composable
fun DashBoardScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Welcome back, Afreen!",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                fontSize = 22.sp,
                color = Color.White
            )
        )
        Spacer(
            modifier = Modifier
                .height(6.dp)
                .fillMaxWidth()
        )

        Text(
            text = "How are you feeling today ?",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                fontSize = 16.sp,
                color = White50
            )
        )
        Spacer(
            modifier = Modifier
                .height(22.dp)
                .fillMaxWidth()
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            Column {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = White90)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.ic_calm),
                        contentDescription = ""
                    )
                }
                Text(
                    text = "Calm",
                    style = TextStyle(

                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            Column {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = White90)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.ic_relax),
                        contentDescription = ""
                    )
                }
                Text(
                    text = "Relax",
                    style = TextStyle(

                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Column {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = White90)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.ic_focus),
                        contentDescription = ""
                    )
                }
                Text(
                    text = "Focus",
                    style = TextStyle(

                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Column {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = White90)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.ic_calm),
                        contentDescription = ""
                    )
                }
                Text(
                    text = "Anxious",
                    style = TextStyle(

                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

        }

        Spacer(
            modifier = Modifier
                .height(22.dp)
                .fillMaxWidth()
        )

        BannerItem(
            title = "Meditation 101",
            description = "Techniques, Benefits,\nand a Beginner’s How-To",
            res = R.drawable.img_illu2
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )

        BannerItem(
            title = "Cardio Meditation",
            description = "Basics of Yoga for Beginners\nor Experienced Professionals",
            res = R.drawable.img_illu1
        )


    }
}

@Composable
fun BannerItem(title: String, description: String, res: Int) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = MyWhite)
            .padding(20.dp)
    ) {

        Image(
            painter = painterResource(id = res),
            contentDescription = "",
            modifier = Modifier.align(Alignment.CenterEnd)
        )

        Column {

            Text(
                text = title,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                    fontSize = 20.sp,
                    color = GreenDark
                )
            )

            Text(
                text = description,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )

            Button(onClick = { /*TODO*/ },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenDark
                )
            ) {

                Row (verticalAlignment = Alignment.CenterVertically){

                    Text(
                        text = "watch now",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                            color = Color.White
                        )
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(6.dp, 0.dp)
                            .size(18.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }


}
