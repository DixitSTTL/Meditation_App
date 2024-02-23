package com.app.meditation.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.meditation.R
import com.app.meditation.ui.theme.GreenLight
import com.app.meditation.ui.theme.White50
import com.app.meditation.ui.theme.White90

@Composable
fun WelcomeScreen(navigateLogin: () -> Unit, navigateSignUp: () -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.img_main_bg),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .blur(20.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_logo_large),
                contentDescription = "",
                modifier = Modifier.size(300.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "WELCOME",
                color = Color.White,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_bold)),
                    fontSize = 30.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Do meditation. Stay focused.\n" + "Live a healthy life.",
                color = White90,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                    fontSize = 20.sp
                ),
                textAlign = TextAlign.Center
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = {
                    navigateLogin()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 30.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenLight
                )
            ) {
                Text(
                    text = "Login With Email",
                    color = White90,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                        fontSize = 20.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Don’t have an account?",
                    color = White50,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                        fontSize = 14.sp
                    )
                )

                Text(
                    text = "Sign Up",
                    color = Color.White,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable {
                            navigateSignUp()
                        }
                )
            }

        }


    }


}

@Composable
fun Preview() {

    WelcomeScreen({}, {})
}

