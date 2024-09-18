package com.app.meditation.ui.screen.positiveNotes

import android.util.Log
import androidx.lifecycle.ViewModel
import com.app.meditation.data.model.NoteModel
import com.app.meditation.domain.usecase.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    var getNoteUseCase: GetNoteUseCase,
) :
    ViewModel() {

    private val _state: MutableStateFlow<NoteState> by lazy { MutableStateFlow(NoteState()) }
    val state: StateFlow<NoteState> = _state

    init {
        getNoteList()
    }

    private fun getNoteList() {

        CoroutineScope(Dispatchers.IO).launch {
            getNoteUseCase.getNotes().collect({
                _state.value = _state.value.copy(noteList = it)
            })
        }
    }

    private fun insertNote() {
        CoroutineScope(Dispatchers.IO).launch {
            getNoteUseCase.insertNote(NoteModel(0, _state.value.title, _state.value.description))
            updateUi()
        }
    }

    private fun updateNote(it: NoteModel) {
        CoroutineScope(Dispatchers.IO).launch {
            getNoteUseCase.updateNote(it)
            updateUi()
        }
    }

    private fun deleteNote(it: NoteModel) {
        CoroutineScope(Dispatchers.IO).launch {
            getNoteUseCase.deleteNotes(it)
        }
    }

    private fun saveNote() {
        if (_state.value.id == 0) {
            insertNote()
        } else {
            updateNote(NoteModel(_state.value.id, _state.value.title, _state.value.description))
        }
    }

    fun onUpdateClick(it: NoteModel) {
        _state.value = _state.value.copy(id = it.id, title = it.title, description = it.description)
        dialogVisibility(true)
    }

    fun onDeleteClick(it: NoteModel) {
        deleteNote(it)
    }

    fun onFloatingButtonClick() {
        updateUi()
        dialogVisibility(true)
    }

    fun onSaveButtonClick() {
        saveNote()
        dialogVisibility(false)
    }

    private fun updateUi() {
        _state.value = _state.value.copy(id = 0, title = "", description = "")
    }

    fun dialogVisibility(boolean: Boolean) {
        _state.value = _state.value.copy(dialogVisible = boolean)
    }

    fun updateTitle(str: String) {
        _state.value = _state.value.copy(title = str)
    }

    fun updateDescription(str: String) {
        _state.value = _state.value.copy(description = str)
    }


}