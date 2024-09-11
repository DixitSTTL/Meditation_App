package com.app.meditation.common

import androidx.compose.ui.graphics.Color
import com.app.meditation.R
import com.app.meditation.data.model.MoodJournal
import com.app.meditation.ui.screen.MainDestinations
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
            icon = R.drawable.ic_journal,
            navigation = MainDestinations.MOOD_JOURNAL
        ),
        DataTools(
            name = "Mood Booster",
            cloudColor = R.color.color_498A78,
            bgColor = R.color.color_69B09C,
            icon = R.drawable.ic_booster,
            navigation = MainDestinations.MOOD_JOURNAL

        ),
        DataTools(
            name = "Positive Notes",
            cloudColor = R.color.color_3E8469,
            bgColor = R.color.color_6AAE72,
            icon = R.drawable.ic_positive,
            navigation = MainDestinations.MOOD_JOURNAL

        ),
        DataTools(
            name = "Trigger Plan",
            cloudColor = R.color.color_6AAE72,
            bgColor = R.color.color_A9D571,
            icon = R.drawable.ic_triggers,
            navigation = MainDestinations.MOOD_JOURNAL

        ),
        DataTools(
            name = "Goal Trainer",
            cloudColor = R.color.color_9A9A9A,
            bgColor = R.color.color_B1B1B1,
            icon = R.drawable.ic_goal,
            navigation = MainDestinations.MOOD_JOURNAL

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

    val moodJournalList = listOf(

        MoodJournal(
            title = "Calm",
            description = " A tranquil state where your mind is at peace, free from distractions or worries. It's a sense of balance and serenity, where you feel present in the moment. You’re able to breathe deeply and move through situations with ease, unaffected by external chaos. Calmness often leads to clearer thinking and emotional stability.",
            icon = R.drawable.ic_calm
        ),
        MoodJournal(
            title = "Relaxed",
            description = "A sense of letting go and unwinding, both mentally and physically. Your muscles feel loose, and your mind is free from stress or heavy thoughts. It’s a time to enjoy the present, without rushing or pressure. Relaxation often brings about a feeling of contentment, as you recharge and restore your energy.",
            icon = R.drawable.ic_relax
        ),
        MoodJournal(
            title = "Focused",
            description = "A sharp, intentional state of mind where all your attention is concentrated on a specific task or goal. Distractions fade into the background, and you feel a sense of control and purpose. Focus allows you to work with efficiency and precision, making progress toward what you want to achieve. It’s a productive, clear-headed mindset.",
            icon = R.drawable.ic_focus
        ),
        MoodJournal(
            title = "Anxious",
            description = "A feeling of unease or nervousness, often caused by uncertainty or overwhelming situations. Your thoughts may race, and your body might feel tense, with sensations like a tight chest or quickened heartbeat. Anxiety can make it difficult to relax or focus, as worry and fear cloud your mind. It’s an emotional state that can feel overwhelming without proper coping mechanisms.",
            icon = R.drawable.ic_anxious
        ),

        )

}