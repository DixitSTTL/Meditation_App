package com.app.meditation.ui.screen.dashbord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.meditation.domain.usecase.GetUseDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private var getUseDataUseCase: GetUseDataUseCase,
) :
    ViewModel() {

    private val _state: MutableStateFlow<DashBoardState> by lazy { MutableStateFlow(DashBoardState()) }
    val state: StateFlow<DashBoardState> = _state

    init {
        getUserData()
    }

    private fun getUserData() {

        viewModelScope.launch(Dispatchers.IO) {
            _state.update {data->
                data.copy(userName = getUseDataUseCase.getUserName())
            }
        }

    }

}