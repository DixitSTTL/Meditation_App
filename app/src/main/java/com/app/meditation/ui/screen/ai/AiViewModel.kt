package com.app.meditation.ui.screen.ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.meditation.domain.usecase.GetAiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiViewModel @Inject constructor(
    private val aiUseCase: GetAiUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(AiState())
    val state: StateFlow<AiState> = _state.asStateFlow()
    val messages = aiUseCase.getMessageList()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun askQuery() {
        viewModelScope.launch {
//            _state.update { it.copy(isLoading = true) }

            try {
                val query = state.value.str
                _state.update { it.copy(str = "") }
                aiUseCase.askQuery(query)
            } catch (e: Exception) {
                e.printStackTrace()
//                _state.update { it.copy(error = e.message) }
            }

//            _state.update { it.copy(isLoading = false) }
        }
    }

    fun setQuery(query: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(str = query)
            }
        }
    }
}