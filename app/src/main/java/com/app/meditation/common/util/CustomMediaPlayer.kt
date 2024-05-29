package com.app.meditation.common.util

import android.media.AudioManager
import android.media.MediaPlayer
import com.app.meditation.ui.screen.tuneList.DataTunes
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.IOException

class CustomMediaPlayer {

    val mediaPlayer = MediaPlayer()

     var dataTunes = MutableStateFlow(DataTunes())
    val isPlaying = MutableStateFlow(false)
    val isPrepared = MutableStateFlow(false)
    val isVisible = MutableStateFlow(false)

    fun loadTune(_dataTunes: DataTunes) {
        isVisible.value = true
        isPrepared.value = false
        dataTunes.value = _dataTunes
        mediaPlayer.reset()


        mediaPlayer.setOnPreparedListener {
            isPrepared.value = true

        }
        mediaPlayer.setOnCompletionListener {
            isPlaying.value = mediaPlayer.isPlaying

        }
        mediaPlayer.setOnSeekCompleteListener {

        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)


        // below line is use to set our
        // url to our media player.
        try {
            mediaPlayer.setDataSource(dataTunes.value.link)

            // below line is use to prepare
            // and start our media player.
            mediaPlayer.prepare()
            mediaPlayer.start()
            isPlaying.value = mediaPlayer.isPlaying

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun playPauseAudio() {

        try {
            if (mediaPlayer.isPlaying) {
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


}