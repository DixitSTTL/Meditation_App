package com.app.meditation.ui.activity.main

import com.app.meditation.ui.screen.tuneList.DataTunes

data class MainState(
   var isPlaying: Boolean = false,
   var isVisible: Boolean = false,
   var isPrepared: Boolean = false,
   var isLooping: Boolean = false,
   var currentProgress: Float = 0f,
   var dataTune: DataTunes = DataTunes()

)