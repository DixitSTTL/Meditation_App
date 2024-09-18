package com.app.meditation.domain.repository

import com.app.meditation.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun getNotes(): Flow<List<NoteModel>>
    suspend fun insertNote(noteModel: NoteModel)
    suspend fun update(noteModel: NoteModel)
    suspend fun deleteNote(noteModel: NoteModel)
}