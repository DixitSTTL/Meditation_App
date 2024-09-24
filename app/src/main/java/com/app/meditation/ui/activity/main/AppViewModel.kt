package com.app.meditation.ui.activity.main

import androidx.lifecycle.ViewModel
import com.app.meditation.domain.usecase.GetMainUseCase
import com.app.meditation.domain.usecase.GetPlayerUseCase
import com.app.meditation.ui.screen.tuneList.DataTunes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private var getPlayerUseCase: GetPlayerUseCase,private val getMainUseCase: GetMainUseCase) :
    ViewModel() {


    fun prepareAudio(it: DataTunes) {
        CoroutineScope(Dispatchers.IO).launch {
            getPlayerUseCase.loadTune(it)
        }

    }

    fun logoutUser() {
        CoroutineScope(Dispatchers.IO).launch {
            getMainUseCase.logoutUser()
        }

    }
    fun playPauseAudio() {

        CoroutineScope(Dispatchers.IO).launch {
            getPlayerUseCase.playPauseAudio()

        }

        // below line is use to display a toast message.
    }


    fun setIsVisible(boolean: Boolean) {
        return getPlayerUseCase.setIsVisible(boolean)
    }

    fun getState(): StateFlow<MainState> {
        return getPlayerUseCase.getState()
    }


}