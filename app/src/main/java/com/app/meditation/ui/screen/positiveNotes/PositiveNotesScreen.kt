package com.app.meditation.ui.screen.positiveNotes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.app.meditation.R
import com.app.meditation.data.model.NoteModel
import com.app.meditation.ui.screen.auth.login.composable.NoteTextInput
import com.app.meditation.ui.theme.GreenLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PositiveNotesScreen(viewmodel: NoteViewModel = hiltViewModel()) {

    val state by viewmodel.state.collectAsState()

    Box(Modifier.fillMaxSize()) {


        if (state.noteList.isEmpty()) {

            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Notes Found",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.alegreya_bold)),
                        fontSize = 22.sp,
                        color = Color.White
                    )
                )
            }

        } else {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.noteList) {
                    NoteItem(
                        it,
                        onDelete = {
                            viewmodel.onDeleteClick(it)
                        },
                        onUpdate = {
                            viewmodel.onUpdateClick(it)
                        })
                }
            }
        }

        ExtendedFloatingActionButton(
            text = { Text("Add") },
            icon = {
                Icon(painter = painterResource(R.drawable.ic_quality), "")
            },
            onClick = {
                viewmodel.onFloatingButtonClick()
            },
            containerColor = GreenLight,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        )

        if (state.dialogVisible) {
            ModalBottomSheet(
                onDismissRequest = {
                    viewmodel.dialogVisibility(false)
                },
                containerColor = Color.Black
            ) {

                Column(modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 100.dp)) {

                    NoteTextInput(state.title, "Enter title", minLines = 1) { str ->
                        viewmodel.updateTitle(str)
                    }

                    NoteTextInput(state.description, "Enter description", minLines = 5) { str ->
                        viewmodel.updateDescription(str)
                    }

                    Button({
                        viewmodel.onSaveButtonClick()

                    }) {
                        Text(
                            "Save",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }


}

@Composable
fun NoteItem(it: NoteModel, onDelete: () -> Unit, onUpdate: () -> Unit) {

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = GreenLight,

            ),
        elevation = CardDefaults.cardElevation(
            focusedElevation = 20.dp,
            defaultElevation = 4.dp
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(1f)
            ) {
                Text(
                    text = it.title,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                        color = Color.White,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )
                Text(
                    text = it.description,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                        color = Color.White
                    )
                )
            }
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically), onClick = {
                    onUpdate()
                }) {
                Icon(painter = painterResource(R.drawable.ic_update), "")
            }
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically), onClick = {
                    onDelete()
                }) {
                Icon(painter = painterResource(R.drawable.ic_delete), "")
            }
        }
    }
}
