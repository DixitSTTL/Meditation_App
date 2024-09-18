package com.app.meditation.domain.usecase

import com.app.meditation.domain.repository.PlayerRepository
import com.app.meditation.ui.activity.main.MainState
import com.app.meditation.ui.screen.tuneList.DataTunes
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetPlayerUseCase @Inject constructor(val playerRepository: PlayerRepository) {


    suspend fun playPauseAudio() {
        playerRepository.playPauseAudio()
    }


    fun getState(): StateFlow<MainState> {
        return playerRepository.getState()
    }


    fun setIsVisible(boolean: Boolean) {
        return playerRepository.setIsVisible(boolean)
    }


    suspend fun loadTune(dataTunes: DataTunes) {
        playerRepository.loadTune(dataTunes)
    }

    fun seekToPosition(float: Float) {
        playerRepository.seekToPosition(float)
    }

    fun setLooping(b: Boolean) {
        playerRepository.setLooping(b)
    }

    fun updateProgress(float: Float) {
        playerRepository.updateProgress(float)
    }
}