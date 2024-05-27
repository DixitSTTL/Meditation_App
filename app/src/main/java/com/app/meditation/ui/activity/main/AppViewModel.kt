package com.app.meditation.ui.activity.main

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.meditation.ui.screen.tuneList.DataTunes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor() :
    ViewModel() {

    val mediaPlayer = MediaPlayer()

    val dataTunes = MutableStateFlow(DataTunes())
    val isPlaying = MutableStateFlow(false)
    val isPreparing = MutableStateFlow(false)
    val isVisible = MutableStateFlow(true)
    fun prepareAudio() {

        viewModelScope.launch {

            val audioUrl = dataTunes.value.link
            isVisible.value = true
            isPreparing.value = true

            // below line is use to set the audio
            // stream type for our media player.
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)


            // below line is use to set our
            // url to our media player.
            try {
                mediaPlayer.setDataSource(audioUrl)

                // below line is use to prepare
                // and start our media player.
                mediaPlayer.prepare()
                mediaPlayer.start()
                isPlaying.value = mediaPlayer.isPlaying

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


    }

    fun play_pause_Audio() {


        try {
            if (isPlaying.value) {
                mediaPlayer.pause()
            } else {
                mediaPlayer.start()

            }
            isPlaying.value = mediaPlayer.isPlaying
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // below line is use to display a toast message.
    }

    fun setDataTune(it: DataTunes) {
        dataTunes.value = it
    }


}