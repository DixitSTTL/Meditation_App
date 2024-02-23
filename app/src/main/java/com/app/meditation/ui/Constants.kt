package com.app.meditation.ui

import androidx.compose.ui.graphics.Color
import com.app.meditation.R
import com.app.meditation.ui.screen.tuneList.DataTunes

object Constants {

    val gradientWhiteColors = listOf(Color.White, Color.White)

    enum class DateValidation {
        FUTURE_DATE,
        PAST_DATE,
        NOT_VALIDATION
    }

    val musicList = listOf(

        DataTunes(
            name = "Painting Forest",
            listener = 5562,
            img = R.drawable.img_tune_5,
            duration = 16
        ),
        DataTunes(
            name = "Lovely Deserts",
            listener = 5322,
            img = R.drawable.img_tune_6,
            duration = 56
        ),
        DataTunes(
            name = "Forest",
            listener = 2544,
            img = R.drawable.img_tune_7,
            duration = 32
        ),
        DataTunes(
            name = "Mountaineers",
            listener = 3222,
            img = R.drawable.img_tune_8,
            duration = 42
        ),
        DataTunes(
            name = "The Hill Sides",
            listener = 3222,
            img = R.drawable.img_tune_9,
            duration = 42
        ),

    )

}