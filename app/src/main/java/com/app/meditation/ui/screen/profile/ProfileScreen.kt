package com.app.meditation.ui.screen.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.meditation.R
import com.app.meditation.ui.theme.GreenDark
import com.app.meditation.ui.theme.GreenLight
import com.app.meditation.ui.theme.White50
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen() {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val titles = listOf("STATS", "ACHIEVEMENTS")
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.img_user),
            contentDescription = "",
            modifier = Modifier
                .size(100.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Afreen Khan", color = Color.White, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_bold)), fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Lucknow, India", color = White50, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.alegreya_regular)), fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(26.dp))

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            divider = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .width(250.dp),
            indicator = {
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                        .tabIndicatorOffset(it[pagerState.currentPage]),
                    color = GreenLight,
                    height = 3.dp
                )
            },
            containerColor = Color.Transparent,
            contentColor = GreenDark,
        ) {

            titles.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            color = if (index == pagerState.currentPage) Color.White else White50,
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.alegreya_bold)),
                                fontSize = 16.sp
                            )
                        )

                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            pageSpacing = 12.dp,
//                pageSize = object : PageSize {
//                    override fun Density.calculateMainAxisPageSize(
//                        availableSpace: Int,
//                        pageSpacing: Int
//                    ): Int {
//                        return ((availableSpace - 2 * pageSpacing) * 0.5f).toInt()
//                    }
//                },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            when (it) {

                0 -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                            .clip(shape = RoundedCornerShape(12.dp))
                            .background(White50)
                    ) {

                    }
                }

                1 -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                            .clip(shape = RoundedCornerShape(12.dp))
                            .background(White50)
                    ) {


                    }

                }
            }


        }

    }

}


@Preview
@Composable
fun PreView() {

    ProfileScreen()
}