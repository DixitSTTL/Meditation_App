package com.app.meditation.ui.screen.ai

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.util.TableInfo
import com.app.meditation.R
import com.app.meditation.data.model.ModelAiChat
import com.app.meditation.ui.screen.auth.login.composable.NoteTextInput
import com.app.meditation.ui.theme.GreenDark
import com.app.meditation.ui.theme.GreenLight
import com.app.meditation.ui.theme.MyWhite
@Composable
fun AiScreen(viewModel: AiViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsStateWithLifecycle().value
    val messageList = viewModel.messages.collectAsStateWithLifecycle().value
    val listState = rememberLazyListState()

    LaunchedEffect(state.messageList.size) {
        listState.animateScrollToItem(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) {

        LazyColumn(
            state = listState,
            reverseLayout = true,
            modifier = Modifier.weight(1f)
        ) {
            items(messageList.reversed()) { item ->
                ChatItem(item)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            NoteTextInput(
                state.str,
                "Enter message",
                minLines = 1,
                modifier = Modifier.weight(1f)
            ) {
                viewModel.setQuery(it)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(GreenLight)
                    .clickable { viewModel.askQuery() },
                contentAlignment = Alignment.Center
            ) {
                Text("➤", color = MyWhite)
            }
        }
    }
}
@Composable
fun ChatItem(item: ModelAiChat) {

    Box(Modifier
        .fillMaxWidth()
        , contentAlignment = if (item.owner == "ai")Alignment.CenterStart else Alignment.CenterEnd) {

        Card(
            modifier = Modifier
                .padding(20.dp, 10.dp)
                .shadow(elevation = 2.dp, spotColor = Color.White, shape = RoundedCornerShape(20.dp)),

            colors = CardDefaults.elevatedCardColors(
                containerColor = GreenLight,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = item.str, style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                            fontSize = 20.sp,
                            color = Color.White
                        ), modifier = Modifier
                            .weight(1f)
                            .padding(0.dp, 4.dp)
                    )

                }

            }
        }
    }


}