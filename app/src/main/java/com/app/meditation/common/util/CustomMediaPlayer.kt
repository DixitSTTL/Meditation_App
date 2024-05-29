package com.app.meditation.common.util

import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import com.app.meditation.ui.screen.tuneList.DataTunes

import kotlinx.coroutines.Runnable
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.IOException

class CustomMediaPlayer : Runnable {

    val mediaPlayer = MediaPlayer()

    var dataTunes = MutableStateFlow(DataTunes())
    val isPlaying = MutableStateFlow(false)
    val isPrepared = MutableStateFlow(false)
    val isVisible = MutableStateFlow(false)
    val currentProgress = MutableStateFlow(0f)

    fun loadTune(_dataTunes: DataTunes) {
        isVisible.value = true
        isPrepared.value = false
        dataTunes.value = _dataTunes
        mediaPlayer.reset()


        mediaPlayer.setOnPreparedListener {
            isPrepared.value = true
            Thread(this).start();
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
                isPlaying.value = mediaPlayer.isPlaying

            } else {
                mediaPlayer.start()
                isPlaying.value = mediaPlayer.isPlaying
                run()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // below line is use to display a toast message.
    }

    override fun run() {
        var currentPosition = mediaPlayer.currentPosition.toFloat()
        val total = mediaPlayer.duration.toFloat()

        while (mediaPlayer.isPlaying) {
            try {
                Thread.sleep(1000)
                currentPosition = mediaPlayer.currentPosition.toFloat()
            } catch (e: InterruptedException) {
                return
            } catch (e: Exception) {
                return
            }
            val f : Float = currentPosition / total
            currentProgress.value = f.toFloat()
        }

    }

    fun seekToPosition(float: Float) {
        val total = mediaPlayer.duration.toFloat()
        val seek = float*total
        mediaPlayer.seekTo(seek.toInt())

    }


}