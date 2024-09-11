package com.app.meditation.data.repository

import com.app.meditation.common.util.CustomMediaPlayer
import com.app.meditation.domain.repository.PlayerRepository
import com.app.meditation.ui.activity.main.MainState
import com.app.meditation.ui.screen.tuneList.DataTunes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlayerRepository_Impl(var customMediaPlayer: CustomMediaPlayer) : PlayerRepository {



    override suspend fun loadTune(dataTunes: DataTunes) {
        customMediaPlayer.loadTune(dataTunes)

    }

    override fun seekToPosition(float: Float) {
        customMediaPlayer.seekToPosition(float)
    }

    override fun setLooping(boolean: Boolean) {
        customMediaPlayer.setLooping(boolean)
    }

    override fun updateProgress(float: Float) {
        customMediaPlayer.updateProgress(float)
    }


    override suspend fun playPauseAudio() {
        customMediaPlayer.playPauseAudio()

    }

    override fun getState(): StateFlow<MainState> {
        return customMediaPlayer.state
    }

    override fun setIsVisible(boolean: Boolean) {
        customMediaPlayer.setIsVisible(boolean)
    }


}