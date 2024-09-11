package com.app.meditation.domain.repository

import com.app.meditation.ui.activity.main.MainState
import com.app.meditation.ui.screen.tuneList.DataTunes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface PlayerRepository {
    suspend fun playPauseAudio()
    fun getState(): StateFlow<MainState>

    fun setIsVisible(boolean: Boolean)

    suspend fun loadTune(dataTunes: DataTunes)
    fun seekToPosition(float: Float)
    fun setLooping(boolean: Boolean)
    fun updateProgress(float: Float)
}