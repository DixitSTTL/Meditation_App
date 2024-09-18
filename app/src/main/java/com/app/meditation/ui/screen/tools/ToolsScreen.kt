package com.app.meditation.ui.screen.tools

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.meditation.R
import com.app.meditation.common.Constants.toolList

@Composable
fun ToolsScreen(onClick: (DataTools) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Tools", color = Color.White, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_bold)), fontSize = 26.sp
            ),
            modifier = Modifier.padding(start = 10.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(toolList) {
                ToolItem(it) {
                    onClick(it)
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

    }

}

@Composable
fun ToolItem(dataTools: DataTools, onClick: (DataTools) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = colorResource(id = dataTools.bgColor))
            .clickable {
                onClick(dataTools)
            }
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_tool_vector), contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(colorResource(id = dataTools.cloudColor))

        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(20.dp)
        ) {

            Icon(
                painter = painterResource(id = dataTools.icon),
                contentDescription = "",
                tint = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = dataTools.name, color = Color.White, style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)), fontSize = 18.sp
                )
            )
        }

    }

}