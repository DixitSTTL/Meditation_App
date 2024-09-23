package com.app.meditation.ui.screen.tuneList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.meditation.domain.usecase.GetTuneListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TuneViewModel @Inject constructor(
    var getTuneListUseCase: GetTuneListUseCase,
) :
    ViewModel() {

    private val _state=MutableStateFlow(TuneListState())
    val state=_state.asStateFlow()

    init {
        getTunes()
    }

    private fun getTunes() {

        _state.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            val responseList = getTuneListUseCase.getTunes()

            _state.update {
                it.copy(isLoading = false,dataList = responseList   )
            }

        }
    }

}