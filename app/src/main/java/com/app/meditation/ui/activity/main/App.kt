package com.app.meditation.ui.activity.main

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.app.MyApp
import com.app.meditation.R
import com.app.meditation.navigation.NavGraph
import com.app.meditation.navigation.TabDestinations
import com.app.meditation.ui.screen.AppDrawer
import com.app.meditation.ui.screen.MainActions
import com.app.meditation.ui.screen.MainDestinations
import com.app.meditation.ui.screen.auth.login.LoginScreen
import com.app.meditation.ui.screen.auth.signUp.SignUpScreen
import com.app.meditation.ui.screen.player.PlayerScreen
import com.app.meditation.ui.screen.welcome.WelcomeScreen
import com.app.meditation.ui.theme.GreenDark
import com.app.meditation.ui.theme.GreenLight
import com.app.meditation.ui.theme.MeditationAppTheme
import com.app.meditation.ui.theme.White30
import com.app.meditation.ui.theme.White50
import kotlinx.coroutines.launch

@Composable
fun App(
    applicationContext: Context,
    widthSizeClass: WindowWidthSizeClass,
    finishActivity: () -> Unit,
    appViewModel: AppViewModel,
    initialScreen: String = MainDestinations.AUTH_ROUTE,
) {

    MeditationAppTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            MainActions(navController, applicationContext, appViewModel)
        }

        Box(
            Modifier
                .fillMaxSize()
                .background(GreenDark)
        ) {
            NavHost(
                navController = navController,
                startDestination = initialScreen
            ) {

                navigation(MainDestinations.WELCOME_ROUTE,MainDestinations.AUTH_ROUTE){
                    composable(MainDestinations.WELCOME_ROUTE) {
                        // Intercept back in Onboarding: make it finish the activity
                        BackHandler {
                            finishActivity()
                        }
                        WelcomeScreen(
                            navigateLogin = { navigationActions.navigateLogin() },
                            navigateSignUp = { navigationActions.navigateSignUp() }
                        )

                    }

                    composable(MainDestinations.LOGIN_ROUTE) { backStackEntry: NavBackStackEntry ->
                        LoginScreen(
                            navigateSignUp = {
                                navigationActions.navigateSignUp()
                            },

                            navigateMainActivity = {
                                navigationActions.navigatetoDashboard()
                            },
                            showToast = { str -> navigationActions.showToast(str) }
                        )


                    }

                    composable(MainDestinations.SIGNUP_ROUTE) { backStackEntry: NavBackStackEntry ->
                        SignUpScreen(
                            navigateLogin = { navigationActions.navigateLogin() },
                            navigateAlreadyLogin = { navigationActions.navigateBack() },
                        )

                    }
                }


                navigation(MainDestinations.DASHBOARD_ROUTE,MainDestinations.MAIN_SCREEN){
                    composable(MainDestinations.DASHBOARD_ROUTE) { backStackEntry: NavBackStackEntry ->
                        DashboardScreen(widthSizeClass = widthSizeClass,
                            finishActivity,
                            onLogoutClick=  {
                                navigationActions.navigateToAuth()

                            })

                    }
                }

            }
        }

    }
}

enum class AppTabs(
    @StringRes val title: Int, @DrawableRes val icon: Int, val route: String
) {
    HOME(R.string.home_tab, R.drawable.ic_logo_main, TabDestinations.HOME_ROUTE),
    TUNES(R.string.tune_tab, R.drawable.ic_tab_tunes, TabDestinations.TUNE_ROUTE),
    PROFILE(R.string.tune_tab, R.drawable.ic_tab_profile, TabDestinations.PROFILE_ROUTE)
}

@Composable
fun BottomBar(navController: NavController) {
    val tabs = remember { AppTabs.entries.toTypedArray() }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: AppTabs.HOME.route

    val routes = remember { AppTabs.entries.map { it.route } }
    if (currentRoute in routes) {

        NavigationBar(containerColor = MaterialTheme.colorScheme.background) {

            tabs.forEach { tab ->

                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(tab.icon),
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
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

@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        // If we want to allow showing the drawer, we use a real, remembered drawer
        // state defined above
        drawerState
    } else {
        // If we don't want to allow the drawer to be shown, we provide a drawer state
        // that is locked closed. This is intentionally not remembered, because we
        // don't want to keep track of any changes and always keep it closed
        DrawerState(DrawerValue.Closed)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    widthSizeClass: WindowWidthSizeClass,
    finishActivity: () -> Unit,
    applicationContext: Context = MyApp.INSTANCE,
    appViewModel: AppViewModel = hiltViewModel(),
    onLogoutClick: ()->Unit
) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: AppTabs.HOME.route
    val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded

    val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)
    val coroutineScope = rememberCoroutineScope()
    val navigationActions = remember(navController) {
        MainActions(navController, applicationContext, appViewModel)
    }
    val canNavigateBack = navController.previousBackStackEntry != null
    val dialogState = remember { mutableStateOf(false) }

    val state by appViewModel.getState().collectAsState()

    ModalNavigationDrawer(
        drawerContent = {
            AppDrawer(
                navigateToMeditation = navigationActions.navigateToMeditation,
                navigateToTools = navigationActions.navigateToTools,
                navigateToSleep = navigationActions.navigateToSleep,
                navigateToLogin = { appViewModel.logoutUser();onLogoutClick() },
                closeDrawer = { coroutineScope.launch { sizeAwareDrawerState.close() } }
            )
        },
        drawerState = sizeAwareDrawerState,
        // Only enable opening the drawer via gestures if the screen is not expanded
        gesturesEnabled = !isExpandedScreen && currentRoute == AppTabs.HOME.route
    ) {
        Scaffold(
            contentColor = MaterialTheme.colorScheme.background,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Image(
                            modifier = Modifier.size(40.dp),
                            painter = painterResource(id = R.drawable.ic_logo_main),
                            contentDescription = "",
                        )

                    },
                    navigationIcon = {
                        Icon(
                            painter = painterResource(id = if (canNavigateBack) R.drawable.ic_back else R.drawable.ic_drawer),
                            contentDescription = "",
                            modifier = Modifier
                                .size(35.dp)
                                .clip(CircleShape)
                                .clickable {
                                    if (currentRoute == AppTabs.HOME.route) {
                                        coroutineScope.launch { sizeAwareDrawerState.open() }
                                    } else {
                                        navController.popBackStack()
                                    }
                                }
                                .padding(5.dp)

                        )
                    }, colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )

                )
            },
            bottomBar = {
                BottomBar(navController = navController)
            }

        ) { innerPaddingModifier ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPaddingModifier)
            ) {

                NavGraph(
                    finishActivity = finishActivity,
                    navController = navController,
                    applicationContext = applicationContext,
                    appViewModel = appViewModel
                )

                if (state.isVisible) {

                    PlayerCard(
                        Modifier
                            .padding(12.dp, 4.dp)
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .clickable {
                                dialogState.value = true
                            }, appViewModel = appViewModel
                    )
                }
            }
        }

        if (dialogState.value) {
            ModalBottomSheet(
                onDismissRequest = {
                    dialogState.value = false

                },
                sheetState = rememberModalBottomSheetState(
                    skipPartiallyExpanded = true,
                    confirmValueChange = { true }
                ),
                containerColor = GreenDark,
                shape = RoundedCornerShape(0),
                dragHandle = {
                    BottomSheetDefaults.DragHandle(color = Color.White)
                }
            ) {
                PlayerScreen(dataTunes = state.dataTune)

            }
        }

    }


}

@Composable
fun PlayerCard(modifier: Modifier, appViewModel: AppViewModel) {
    val state by appViewModel.getState().collectAsState()
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12),
        colors = CardDefaults.elevatedCardColors(
            containerColor = GreenLight
        )
    ) {

        Row(
            modifier = Modifier
                .padding(8.dp, 6.dp, 8.dp, 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = state.dataTune.image,
                contentDescription = "item.name",
                modifier = Modifier
                    .size(42.dp)
                    .clip(shape = RoundedCornerShape(16)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.Top)
            ) {
                Text(
                    text = state.dataTune.name.toString(),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                        fontSize = 16.sp,
                        color = Color.White
                    )
                )
                Text(
                    text = "${state.dataTune.listener} Monthly listenersff",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                        fontSize = 12.sp,
                        color = Color.White
                    )
                )


            }

            Spacer(modifier = Modifier.width(16.dp))

            AnimatedVisibility(visible = state.isPrepared) {
                Icon(
                    painter = painterResource(
                        id = if (state.isPlaying) {
                            R.drawable.ic_pause
                        } else {
                            R.drawable.ic_play
                        }
                    ),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            appViewModel.playPauseAudio()
                        }
                )
            }

            AnimatedVisibility(visible = !state.isPrepared) {
                CircularProgressIndicator(
                    modifier = Modifier,
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            }
        }

        LinearProgressIndicator(
            progress = state.currentProgress,
            color = Color.White,
            trackColor = White30,
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .padding(horizontal = 10.dp)
                .clip(shape = RoundedCornerShape(50))
        )
    }

}