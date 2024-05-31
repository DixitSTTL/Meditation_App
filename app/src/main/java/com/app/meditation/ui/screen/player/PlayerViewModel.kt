package com.app.meditation.ui.screen.player

import android.util.Log
import androidx.lifecycle.ViewModel
import com.app.meditation.domain.usecase.GetPlayerUseCase
import com.app.meditation.ui.screen.tuneList.DataTunes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlayerViewModel @Inject constructor(var getPlayerUseCase: GetPlayerUseCase) : ViewModel() {

    var middleSeeker :MutableStateFlow<Float> = getPlayerUseCase.getCurrentProgress()
    var isLooping :MutableStateFlow<Boolean> = getPlayerUseCase.getIsLooping()

    fun playPauseAudio() {

        CoroutineScope(Dispatchers.IO).launch {
            getPlayerUseCase.play_pause_Audio()

        }

        // below line is use to display a toast message.
    }

    fun getIsPlaying(): MutableStateFlow<Boolean> {
        return getPlayerUseCase.getIsPlaying()
    }
    fun getIsVisible(): MutableStateFlow<Boolean> {
        return getPlayerUseCase.getIsVisible()
    }
    fun getIsisPrepared(): MutableStateFlow<Boolean> {
        return getPlayerUseCase.getIsisPrepared()
    }
    fun getCurrentProgress(): MutableStateFlow<Float> {
        return getPlayerUseCase.getCurrentProgress()
    }

    fun getIsLooping(): MutableStateFlow<Boolean> {
        return getPlayerUseCase.getIsLooping()
    }
    fun getDataTunes(): MutableStateFlow<DataTunes> {
        return getPlayerUseCase.getIsDataTunes()
    }

    fun seekToPosition(float: Float) {
        Log.d("vvs","$float")
        getPlayerUseCase.seekToPosition(float)
    }

    fun setLooping(b: Boolean) {
        getPlayerUseCase.setLooping(b)
    }

}