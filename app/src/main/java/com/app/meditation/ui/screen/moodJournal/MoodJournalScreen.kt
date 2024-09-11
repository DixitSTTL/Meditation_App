package com.app.meditation.ui.screen.moodJournal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.meditation.R
import com.app.meditation.common.Constants.moodJournalList
import com.app.meditation.data.model.MoodJournal
import com.app.meditation.ui.theme.GreenLight

@Composable
fun MoodJournalScreen() {

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(moodJournalList) { item ->

                MoodItem(item)
            }

        }
    }


}

@Composable
fun MoodItem(item: MoodJournal) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 10.dp)
            .shadow( elevation = 10.dp, spotColor = Color.White, shape = RoundedCornerShape(20.dp)),

        colors = CardDefaults.elevatedCardColors(
            containerColor = GreenLight,
        )) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Image(painter = painterResource(item.icon), "",Modifier.size(50.dp))
            Text(
                text = item.title, style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                    fontSize = 20.sp,
                    color = Color.White
                ), modifier = Modifier.padding(0.dp, 4.dp)
            )
            Text(
                text = item.description, style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_talic)),
                    color = Color.White
                )
            )

        }
    }


}