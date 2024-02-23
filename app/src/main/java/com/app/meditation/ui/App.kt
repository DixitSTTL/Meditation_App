package com.app.meditation.ui

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.meditation.R
import com.app.meditation.ui.theme.GreenLight
import com.app.meditation.ui.theme.MeditationAppTheme
import com.app.meditation.ui.theme.White50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(applicationContext: Context, finishActivity: () -> Unit) {


    MeditationAppTheme {
        val tabs = remember { AppTabs.entries.toTypedArray() }

        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: AppTabs.HOME.route

        Scaffold(
            contentColor = MaterialTheme.colorScheme.background,
            topBar = {
                CenterAlignedTopAppBar(title = {

                    /*    Text(
                            text = "COVID-19 measures",
                            style = TextStyle(
                                fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.rubik_medium))
                            ),
                            color = White_cl_90,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )*/
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.ic_logo_large),
                        contentDescription = ""
                    )


                },
                    navigationIcon = {
                    Icon(
                        painter =painterResource(id = if (currentRoute==AppTabs.HOME.route)  R.drawable.ic_drawer else R.drawable.ic_back) ,
                        contentDescription = "",
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .clickable {
                                if (currentRoute==AppTabs.HOME.route){
//                                    navController.popBackStack()
                                }else{
                                    navController.popBackStack()
                                }
//                                navigateBack()
                            }
                            .padding(5.dp)

                    )
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )

                )
            },
            bottomBar = {
                BottomBar(navController = navController, tabs)
            }

        ) { innerPaddingModifier ->
            NavGraph(
                finishActivity = finishActivity,
                navController = navController,
                modifier = Modifier.padding(innerPaddingModifier),
                applicationContext = applicationContext
            )
        }
    }
}

enum class AppTabs(
    @StringRes val title: Int, @DrawableRes val icon: Int, val route: String
) {
    HOME(R.string.home_tab, R.drawable.ic_tab_home, TabDestinations.HOME_ROUTE),
    TUNES(R.string.tune_tab, R.drawable.ic_tab_tunes, TabDestinations.TUNE_ROUTE),
    PROFILE(R.string.tune_tab, R.drawable.ic_tab_profile, TabDestinations.PROFILE_ROUTE)
}

@Composable
fun BottomBar(navController: NavController, tabs: Array<AppTabs>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: AppTabs.HOME.route

    val routes = remember { AppTabs.values().map { it.route } }
    if (currentRoute in routes) {

        NavigationBar(containerColor = MaterialTheme.colorScheme.background) {

            tabs.forEach { tab ->

                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(tab.icon),
                            contentDescription = null,
                            modifier = Modifier,
                            tint = if (currentRoute == tab.route) Color.White else White50
                        )
                    },
                    alwaysShowLabel = false,
                    selected = currentRoute == tab.route,
                    onClick = {
                        if (tab.route != currentRoute) {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = GreenLight,
                    ),
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        }
    }
}