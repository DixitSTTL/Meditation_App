package com.app.meditation.domain.usecase

import com.app.meditation.domain.repository.PlayerRepository
import com.app.meditation.ui.screen.tuneList.DataTunes
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class GetPlayerUseCase @Inject constructor(val playerRepository: PlayerRepository) {


    suspend fun startTune() {

        playerRepository.startTune()
    }

    suspend fun playTune() {
        playerRepository.playTune()

    }

    suspend fun pauseTune() {
        playerRepository.pauseTune()

    }

    suspend fun play_pause_Audio() {
        playerRepository.playPauseAudio()
    }

    fun getIsPlaying(): MutableStateFlow<Boolean> {

        return playerRepository.getIsPlaying()
    }

    fun getIsVisible(): MutableStateFlow<Boolean> {

        return playerRepository.getIsVisible()
    }
    fun setIsVisible(boolean: Boolean) {
        return playerRepository.setIsVisible(boolean)
    }

    fun getIsisPrepared(): MutableStateFlow<Boolean> {

        return playerRepository.getIsisPrepared()
    }
    fun getCurrentProgress(): MutableStateFlow<Float> {

        return playerRepository.getCurrentProgress()
    }

    fun getIsDataTunes(): MutableStateFlow<DataTunes> {

        return playerRepository.getDataTunes()
    }

    suspend fun loadTune(dataTunes: DataTunes) {
        playerRepository.loadTune(dataTunes)
    }

    fun seekToPosition(float: Float) {
        playerRepository.seekToPosition(float)
    }

    fun getIsLooping(): MutableStateFlow<Boolean> {
        return playerRepository.getLooping()
    }

    fun setLooping(b: Boolean) {
        playerRepository.setLooping(b)
    }
}