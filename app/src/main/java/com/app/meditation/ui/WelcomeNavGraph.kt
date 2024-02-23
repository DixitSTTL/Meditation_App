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

package com.app.meditation.ui

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.meditation.ui.screen.login.LoginScreen
import com.app.meditation.ui.screen.signUp.SignUpScreen
import com.app.meditation.ui.screen.welcome.WelcomeScreen

@Composable
fun WelcomeNavGraph(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navigateToMain: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = WelcomeDestinations.WELCOME_ROUTE,
    showOnboardingInitially: Boolean = true,
    applicationContext: Context
) {
    // Onboarding could be read from shared preferences.
    val onboardingComplete = remember(showOnboardingInitially) {
        mutableStateOf(!showOnboardingInitially)
    }

    val actions = remember(navController) { WelcomeActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(WelcomeDestinations.WELCOME_ROUTE) {
            // Intercept back in Onboarding: make it finish the activity
            BackHandler {
                finishActivity()
            }
            WelcomeScreen(
                navigateLogin = { actions.navigateLogin() },
                navigateSignUp = { actions.navigateSignUp() }
            )

        }

        composable(WelcomeDestinations.LOGIN_ROUTE) { backStackEntry: NavBackStackEntry ->
            LoginScreen(
                navigateBack = {
                    actions.navigateBack()
                },
                navigateSignUp = {
                    actions.navigateSignUp()
                },

                navigateMainActivity= {
                    navigateToMain()
                })


        }

        composable(WelcomeDestinations.SIGNUP_ROUTE) { backStackEntry: NavBackStackEntry ->
            SignUpScreen(navigateBack = {
                actions.navigateBack()
            },
                navigateSignUp = { actions.navigateLogin() })

        }

    }
}

object WelcomeDestinations {
    const val WELCOME_ROUTE = "welcomeRoute"
    const val LOGIN_ROUTE = "loginRoute"
    const val SIGNUP_ROUTE = "signupRoute"

    const val DATATTUNE_KEY = "datattune_key"

}

/**
 * Models the navigation actions in the app.
 */
class WelcomeActions(navController: NavHostController) {
    val navigateBack: () -> Unit = {
        navController.popBackStack()
    }

    val navigateLogin = {
        navController.navigate(WelcomeDestinations.LOGIN_ROUTE)
    }

    val navigateSignUp = {
        navController.navigate(WelcomeDestinations.SIGNUP_ROUTE)
    }

}