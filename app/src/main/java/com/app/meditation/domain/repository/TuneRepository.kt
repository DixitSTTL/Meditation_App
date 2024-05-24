package com.app.meditation.domain.repository

import com.app.meditation.ui.screen.tuneList.DataTunes

interface TuneRepository {
    suspend fun getTunes():MutableList<DataTunes>
}