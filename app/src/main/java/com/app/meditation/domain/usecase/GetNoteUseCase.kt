package com.app.meditation.domain.usecase

import com.app.meditation.data.model.NoteModel
import com.app.meditation.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(val noteRepository: NoteRepository) {

    suspend fun getNotes(): Flow<List<NoteModel>> {
        return noteRepository.getNotes()
    }
    suspend fun insertNote(noteModel: NoteModel) {
        return noteRepository.insertNote(noteModel)
    }
    suspend fun updateNote(noteModel: NoteModel) {
        return noteRepository.update(noteModel)
    }
    suspend fun deleteNotes(noteModel: NoteModel){
        return noteRepository.deleteNote(noteModel)
    }
}