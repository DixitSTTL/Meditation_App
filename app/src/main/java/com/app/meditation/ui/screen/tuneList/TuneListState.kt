package com.app.meditation.ui.screen.tuneList

data class TuneListState(
    val isLoading:Boolean = false,
    val dataList :List<DataTunes> = emptyList<DataTunes>()
)
