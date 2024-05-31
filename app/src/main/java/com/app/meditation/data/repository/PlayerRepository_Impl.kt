package com.app.meditation.data.repository

import com.app.meditation.common.util.CustomMediaPlayer
import com.app.meditation.domain.repository.PlayerRepository
import com.app.meditation.ui.screen.tuneList.DataTunes
import kotlinx.coroutines.flow.MutableStateFlow

class PlayerRepository_Impl(var customMediaPlayer: CustomMediaPlayer) : PlayerRepository {

    override suspend fun startTune() {

        customMediaPlayer.mediaPlayer.prepare()
        customMediaPlayer.mediaPlayer.start()
    }

    override suspend fun playTune() {
        customMediaPlayer.mediaPlayer.start()

    }

    override suspend fun pauseTune() {
        customMediaPlayer.mediaPlayer.pause()

    }

    override suspend fun loadTune(dataTunes: DataTunes) {

        customMediaPlayer.loadTune(dataTunes)

    }

    override fun seekToPosition(float: Float) {
        customMediaPlayer.seekToPosition(float)
    }

    override fun setLooping(boolean: Boolean) {
        customMediaPlayer.setLooping(boolean)
    }

    override fun getLooping(): MutableStateFlow<Boolean> {
        return customMediaPlayer.getLooping()
    }

    override suspend fun playPauseAudio() {
        customMediaPlayer.playPauseAudio()

    }

    override fun getIsVisible(): MutableStateFlow<Boolean> {
        return customMediaPlayer.isVisible
    }

    override fun setIsVisible(boolean: Boolean) {
        customMediaPlayer.isVisible.value = boolean
    }


    override fun getIsPlaying(): MutableStateFlow<Boolean> {
       return customMediaPlayer.isPlaying
    }
    override fun getIsisPrepared(): MutableStateFlow<Boolean> {
       return customMediaPlayer.isPrepared
    }

    override fun getCurrentProgress(): MutableStateFlow<Float> {
        return customMediaPlayer.currentProgress
    }

    override fun getDataTunes(): MutableStateFlow<DataTunes> {
       return customMediaPlayer.dataTunes
    }
}