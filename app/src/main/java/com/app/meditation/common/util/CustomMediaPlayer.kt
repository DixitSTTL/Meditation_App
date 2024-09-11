package com.app.meditation.common.util

import android.media.AudioManager
import android.media.MediaPlayer
import com.app.meditation.ui.activity.main.MainState
import com.app.meditation.ui.screen.tuneList.DataTunes
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.IOException

class CustomMediaPlayer : Runnable {

    val mediaPlayer = MediaPlayer()
    private val mState = MutableStateFlow(MainState())
    val state = mState.asStateFlow()

    fun loadTune(dataTunes: DataTunes) {
        mState.update { it.copy(isPrepared = false, isVisible = true, dataTune = dataTunes) }
        mediaPlayer.reset()


        mediaPlayer.setOnPreparedListener {
            mState.update { it.copy(isPrepared = true) }

            Thread(this).start();
        }
        mediaPlayer.setOnCompletionListener {
            mState.update { it.copy(isPlaying = mediaPlayer.isPlaying) }

        }
        mediaPlayer.setOnSeekCompleteListener {

        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)


        // below line is use to set our
        // url to our media player.
        try {
            mediaPlayer.setDataSource(state.value.dataTune.link)

            // below line is use to prepare
            // and start our media player.
            mediaPlayer.prepare()
            mediaPlayer.start()
            mState.update { it.copy(isPlaying = mediaPlayer.isPlaying) }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun playPauseAudio() {

        try {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                mState.update { it.copy(isPlaying = false) }

            } else {
                mediaPlayer.start()
                mState.update { it.copy(isPlaying = mediaPlayer.isPlaying) }

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
                Thread.sleep(200)
                currentPosition = mediaPlayer.currentPosition.toFloat()
            } catch (e: InterruptedException) {
                return
            } catch (e: Exception) {
                return
            }
            val f: Float = currentPosition / total
            mState.update { it.copy(currentProgress = f) }

        }

    }

    fun seekToPosition(float: Float) {
        val total = mediaPlayer.duration.toFloat()
        val seek = float * total
        mediaPlayer.seekTo(seek.toInt())

    }

    fun setIsVisible(boolean: Boolean) {
        mState.update { it.copy(isVisible = boolean) }


    }

    fun setLooping(boolean: Boolean) {
        mediaPlayer.isLooping = boolean
        mState.update { it.copy(isLooping = mediaPlayer.isLooping) }


    }

    fun updateProgress(float: Float) {
        mState.update { it.copy(currentProgress = float) }
    }


}