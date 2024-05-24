package com.app.meditation.ui.screen.player

import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class PlayerViewModel @Inject constructor() : ViewModel() {

    fun playAudio(link: String?) {
        val audioUrl = link


        // initializing media player
        val mediaPlayer = MediaPlayer()


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
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // below line is use to display a toast message.
    }

}