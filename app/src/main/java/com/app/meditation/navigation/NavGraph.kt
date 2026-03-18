/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.meditation.navigation

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.meditation.ui.activity.main.AppTabs
import com.app.meditation.ui.activity.main.AppViewModel
import com.app.meditation.ui.screen.MainActions
import com.app.meditation.ui.screen.MainDestinations
import com.app.meditation.ui.screen.dashbord.DashBoardScreen
import com.app.meditation.ui.screen.meditation.MeditationScreen
import com.app.meditation.ui.screen.moodBooster.MoodBoosterScreen
import com.app.meditation.ui.screen.moodJournal.MoodJournalScreen
import com.app.meditation.ui.screen.player.PlayerScreen
import com.app.meditation.ui.screen.positiveNotes.PositiveNotesScreen
import com.app.meditation.ui.screen.profile.ProfileScreen
import com.app.meditation.ui.screen.sleep.SleepScreen
import com.app.meditation.ui.screen.tools.ToolsScreen
import com.app.meditation.ui.screen.tuneList.DataTunes
import com.app.meditation.ui.screen.tuneList.TuneListScreen
import com.google.gson.Gson

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppTabs.HOME.route,
    showOnboardingInitially: Boolean = true,
    applicationContext: Context,
    appViewModel: AppViewModel = hiltViewModel()

) {
    // Onboarding could be read from shared preferences.
    val onboardingComplete = remember(showOnboardingInitially) {
        mutableStateOf(!showOnboardingInitially)
    }

    val actions =
        remember(navController) { MainActions(navController, applicationContext, appViewModel) }


    Box(modifier = Modifier) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,

            ) {
            composable(AppTabs.HOME.route) {
                // Intercept back in Onboarding: make it finish the activity
                BackHandler {
                    finishActivity()
                }

                DashBoardScreen(
                    cardioClick = {
                        actions.navigateCardio()

                    },
                    meditationClick = {
                        actions.navigateMeditation()
                    }
                )

            }

            composable(AppTabs.TUNES.route) {
                BackHandler {
                    actions.navigateBack()
                }
                TuneListScreen(
                    dataTunes = { dataTune ->
                        actions.prepareAudio(dataTune)
                    }

                )


            }

            composable(AppTabs.PROFILE.route) { backStackEntry: NavBackStackEntry ->
                BackHandler {
                    actions.navigateBack()
                }

                ProfileScreen()

            }

            composable(MainDestinations.MEDITATION_ROUTE) { backStackEntry: NavBackStackEntry ->
                BackHandler {
                    actions.navigateBack()
                }

                MeditationScreen()

            }

            composable(MainDestinations.TOOLS_ROUTE) { backStackEntry: NavBackStackEntry ->
                BackHandler {
                    actions.navigateBack()
                }

                ToolsScreen(onClick = { dataTool ->

                    actions.navigateToTool(dataTool.navigation)
                })


            }

            composable(MainDestinations.SLEEP_ROUTE) { backStackEntry: NavBackStackEntry ->
                BackHandler {
                    actions.navigateBack()
                }

                SleepScreen()

            }

            composable(route = MainDestinations.MOOD_JOURNAL) { backStackEntry: NavBackStackEntry ->
                MoodJournalScreen()

            }

            composable(route = MainDestinations.MOOD_BOOSTER) { backStackEntry: NavBackStackEntry ->
                MoodBoosterScreen()
            }

            composable(route = MainDestinations.POSITIVE_NOTES) { backStackEntry: NavBackStackEntry ->
                PositiveNotesScreen()
            }

        }

    }

}


/**
 * Models the navigation actions in the app.
 */


object TabDestinations {
    const val HOME_ROUTE = "tab/home"
    const val TUNE_ROUTE = "tab/tune"
    const val PROFILE_ROUTE = "tab/profile"
}

class DataTunesArgType : NavType<DataTunes>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): DataTunes? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): DataTunes {
        return Gson().fromJson(value, DataTunes::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: DataTunes) {
        bundle.putParcelable(key, value)
    }
}


