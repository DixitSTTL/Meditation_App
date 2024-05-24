package com.app.meditation.ui.screen.tuneList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.meditation.domain.usecase.GetTuneListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TuneViewModel @Inject constructor(
    var getTuneListUseCase: GetTuneListUseCase,
) :
    ViewModel() {

    private val _musicList = MutableStateFlow<MutableList<DataTunes>>(mutableListOf())
    val musicList get() = _musicList.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading get() = _isLoading.asStateFlow()

    init {
        getTunes()
    }

    private fun getTunes() {

        _isLoading.value = true
        viewModelScope.launch {
            val dataList = getTuneListUseCase.getTunes()
            _isLoading.value = false
            _musicList.value = dataList
        }
    }

}