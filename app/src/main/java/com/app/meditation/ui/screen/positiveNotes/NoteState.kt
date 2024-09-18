package com.app.meditation.ui.screen.positiveNotes

import com.app.meditation.data.model.NoteModel

data class NoteState(
    var dialogVisible: Boolean = false,
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var noteList: List<NoteModel> = emptyList()
)
