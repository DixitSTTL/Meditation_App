package com.app.meditation.ui.screen.meditation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun MeditationScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Meditation", color = Color.White, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_bold)), fontSize = 26.sp
            )
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Guided by a short introductory course,\n" +
                    "start trying meditation.", color = White50, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_regular)), fontSize = 16.sp,
                        textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_meditation),
            contentDescription = "",
            modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "45:00", color = Color.White, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)), fontSize = 26.sp
            )

        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenLight
            )
        ) {
            Text(
                text = " Start Now",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                    color = Color.White
                ),
                fontSize = 20.sp
            )
        }
    }

}