package com.app.meditation.common

import androidx.compose.ui.graphics.Color
import com.app.meditation.R
import com.app.meditation.data.model.MoodBooster
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
            navigation = MainDestinations.MOOD_BOOSTER

        ),
        DataTools(
            name = "Positive Notes",
            cloudColor = R.color.color_3E8469,
            bgColor = R.color.color_6AAE72,
            icon = R.drawable.ic_positive,
            navigation = MainDestinations.POSITIVE_NOTES

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

    val moodBoosterList = listOf(

        MoodBooster(
            title = "\"Happiness is not by chance, but by choice.\"",
            description = "      Happiness is not something that comes randomly or only when circumstances are perfect.\n\n      It’s a mindset that can be nurtured by consciously deciding how to respond to life’s situations. By choosing gratitude, mindfulness, and positivity, you can create a life filled with happiness, regardless of external conditions.",
        ),
        MoodBooster(
            title = "\"The mind is everything. What you think, you become.\"",
            description = "      Your thoughts have immense power over your actions and feelings. If you constantly focus on negativity, you’re likely to attract negative experiences.\n\n      Conversely, positive thinking shapes your behavior in constructive ways, bringing you closer to your aspirations. Your reality is a reflection of your inner thought patterns.",
        ),
        MoodBooster(
            title = "\"Breathe in peace, breathe out stress\"",
            description = "      This quote emphasizes the power of conscious breathing in controlling emotions. By focusing on your breath, you bring awareness to the present moment, creating space to release tension and anxiety.\n\n      Each inhale invites tranquility, while each exhale is an opportunity to let go of worries, symbolizing a mental cleanse.",
        ),
        MoodBooster(
            title = "\"Let go of what you can't control.\"",
            description = "      Life often presents challenges that are beyond our control, and clinging to the need to control everything leads to unnecessary stress.\n\n      By accepting the flow of life and focusing on what you can change, you release the burden of resistance. This acceptance opens up space for peace and personal growth.",
        ),
        MoodBooster(
            title = "\"Stillness is where creativity and solutions are found.\"",
            description = "      In our fast-paced world, we often rush through life without pausing to think. When you allow yourself to be still, your mind becomes more receptive to new ideas and solutions.\n\n      Stillness gives space for clarity to emerge, helping you see things from a fresh perspective and unlock deeper levels of creativity.",
        ),
        MoodBooster(
            title = "\"Inhale confidence, exhale doubt.\"",
            description = "      This phrase encourages using your breath as a powerful tool to shift your mindset. With every inhale, imagine absorbing self-belief and inner strength.\n\n      As you exhale, consciously let go of any fear or self-doubt that holds you back. It’s a simple yet powerful exercise to build confidence from within.",
        ),
        MoodBooster(
            title = "\"You are stronger than you think\"",
            description = "      Often, people underestimate their ability to endure and overcome challenges. This quote is a reminder that inner strength often reveals itself in times of difficulty.\n\n      You are far more resilient than you give yourself credit for, and you can rise above adversity with persistence and self-belief.",
        ),
        MoodBooster(
            title = "\"Every day is a new beginning\"",
            description = "      No matter what happened yesterday, today is an opportunity to start fresh. You don’t have to carry the weight of past mistakes or regrets.\n\n      Each morning brings a clean slate, giving you the freedom to reset your mindset and make new choices that align with your best self."
        ),
        MoodBooster(
            title = "\"Your calm mind is the ultimate weapon against your challenges.\"",
            description = "      When faced with difficulties, a peaceful mind is your greatest asset. Chaos and stress cloud judgment, but approaching problems with calmness allows you to think clearly and make sound decisions.\n\n      Serenity helps you stay composed in the face of obstacles, turning challenges into manageable tasks.",
        ),
        MoodBooster(
            title = "\"Peace begins with a smile.\"",
            description = "      A simple smile can initiate a powerful shift in both your mood and the energy around you.\n\n      Smiling, even when you don’t feel like it, can instantly lift your spirits and create a ripple of positive energy. Inner peace often starts with small, mindful gestures, and a smile is the first step."

        ),


        )

}