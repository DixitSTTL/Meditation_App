package com.app.meditation.data.repository

import com.app.meditation.data.model.NoteModel
import com.app.meditation.database.DatabaseHelper
import com.app.meditation.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepository_Impl(var databaseHelper: DatabaseHelper) : NoteRepository {

    override suspend fun getNotes(): Flow<List<NoteModel>> {
        return databaseHelper.noteDao.getAllNotes()
    }

    override suspend fun insertNote(noteModel: NoteModel) {
        databaseHelper.noteDao.insert(noteModel)
    }

    override suspend fun update(noteModel: NoteModel) {
        databaseHelper.noteDao.update(noteModel)
    }

    override suspend fun deleteNote(noteModel: NoteModel) {
        databaseHelper.noteDao.delete(noteModel)
    }
}