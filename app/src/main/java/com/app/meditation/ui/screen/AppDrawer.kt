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

package com.app.meditation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.app.meditation.R
import com.app.meditation.ui.theme.GreenDark

@Composable
fun AppDrawer(
    currentRoute: String?,
    navigateToMeditation: () -> Unit,
    navigateToTools: () -> Unit,
    navigateToSleep: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(modifier, drawerContainerColor = GreenDark) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column {

                Spacer(modifier = Modifier.height(20.dp))

                NavigationDrawerItem(
                    label = {
                        Text(
                            stringResource(id = R.string.meditation),
                            fontFamily = FontFamily(Font(R.font.alegreya_semi_bold))
                        )
                    },
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.ic_focus),
                            null,
                            Modifier.size(26.dp)
                        )
                    },
                    selected = currentRoute == JetnewsDestinations.HOME_ROUTE,
                    onClick = { navigateToMeditation(); closeDrawer() },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                Spacer(modifier = Modifier.height(6.dp))

                NavigationDrawerItem(
                    label = {
                        Text(
                            stringResource(id = R.string.tools),
                            fontFamily = FontFamily(Font(R.font.alegreya_semi_bold))
                        )
                    },
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.ic_journal),
                            null,
                            Modifier.size(26.dp)
                        )
                    },
                    selected = currentRoute == JetnewsDestinations.INTERESTS_ROUTE,
                    onClick = { navigateToTools(); closeDrawer() },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                Spacer(modifier = Modifier.height(6.dp))

                NavigationDrawerItem(
                    label = {
                        Text(
                            stringResource(id = R.string.sleep),
                            fontFamily = FontFamily(Font(R.font.alegreya_semi_bold))
                        )
                    },
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.ic_sleep),
                            null,
                            Modifier.size(26.dp)
                        )
                    },
                    selected = currentRoute == JetnewsDestinations.INTERESTS_ROUTE,
                    onClick = { navigateToSleep(); closeDrawer() },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }

        }


    }
}


object JetnewsDestinations {
    const val HOME_ROUTE = "home"
    const val INTERESTS_ROUTE = "interests"
}