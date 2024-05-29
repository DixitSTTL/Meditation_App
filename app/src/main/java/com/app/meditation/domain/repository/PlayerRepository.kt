package com.app.meditation.domain.repository

import com.app.meditation.ui.screen.tuneList.DataTunes
import kotlinx.coroutines.flow.MutableStateFlow

interface PlayerRepository {
    suspend fun startTune()
    suspend fun playTune()
    suspend fun pauseTune()
    suspend fun playPauseAudio()
    fun getIsPlaying():MutableStateFlow<Boolean>
    fun getIsVisible():MutableStateFlow<Boolean>
    fun setIsVisible(boolean: Boolean)
    fun getDataTunes():MutableStateFlow<DataTunes>
    fun getIsisPrepared():MutableStateFlow<Boolean>
    fun getCurrentProgress():MutableStateFlow<Float>
    suspend fun loadTune(dataTunes: DataTunes)
    fun seekToPosition(float: Float)
}