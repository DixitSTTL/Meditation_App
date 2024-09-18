package com.app.meditation.ui.screen.player

import android.util.Log
import androidx.lifecycle.ViewModel
import com.app.meditation.domain.usecase.GetPlayerUseCase
import com.app.meditation.ui.activity.main.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlayerViewModel @Inject constructor(var getPlayerUseCase: GetPlayerUseCase) : ViewModel() {

    fun playPauseAudio() {

        CoroutineScope(Dispatchers.IO).launch {
            getPlayerUseCase.playPauseAudio()

        }

        // below line is use to display a toast message.
    }


    fun getState(): StateFlow<MainState> {
        return getPlayerUseCase.getState()
    }


    fun seekToPosition(float: Float) {
        Log.d("vvs", "$float")
        getPlayerUseCase.seekToPosition(float)
    }

    fun setLooping(b: Boolean) {
        getPlayerUseCase.setLooping(b)
    }

    fun updateProgress(per: Float) {
        getPlayerUseCase.updateProgress(per)
    }

}