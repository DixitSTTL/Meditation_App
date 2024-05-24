package com.app.meditation.common

import androidx.compose.ui.graphics.Color
import com.app.meditation.R
import com.app.meditation.ui.screen.sleep.DataSleep
import com.app.meditation.ui.screen.tools.DataTools

object Constants {

    val gradientWhiteColors = listOf(Color.White, Color.White)
    const val PREF_NAME = "prefMeditation"

    enum class DateValidation {
        FUTURE_DATE,
        PAST_DATE,
        NOT_VALIDATION
    }


    val toolList = listOf(

        DataTools(
            name = "Mood Journal",
            cloudColor = R.color.color_2B5B54,
            bgColor = R.color.color_3E8469,
            icon = R.drawable.ic_journal
        ),
        DataTools(
            name = "Mood Booster",
            cloudColor = R.color.color_498A78,
            bgColor = R.color.color_69B09C,
            icon = R.drawable.ic_booster
        ),
        DataTools(
            name = "Positive Notes",
            cloudColor = R.color.color_3E8469,
            bgColor = R.color.color_6AAE72,
            icon = R.drawable.ic_positive
        ),
        DataTools(
            name = "Trigger Plan",
            cloudColor = R.color.color_6AAE72,
            bgColor = R.color.color_A9D571,
            icon = R.drawable.ic_triggers
        ),
        DataTools(
            name = "Goal Trainer",
            cloudColor = R.color.color_9A9A9A,
            bgColor = R.color.color_B1B1B1,
            icon = R.drawable.ic_goal
        )

    )

    val sleepList = listOf(

        DataSleep(
            title = "Sleep",
            value = "5h 30m",
            bgColor = R.color.color_69B09C,
            icon = R.drawable.ic_sleep
        ),
        DataSleep(
            title = "Deep",
            value = "1h 10m",
            bgColor = R.color.color_498A78,
            icon = R.drawable.ic_deep
        ),
        DataSleep(
            title = "Quality",
            value = "3h 30m",
            bgColor = R.color.color_69B09C,
            icon = R.drawable.ic_quality
        )
    )

}