package com.jarica.chronogol.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jarica.chronogol.presentation.navigation.Graph.ROOT_GRAPH
import com.jarica.chronogol.presentation.navigation.destinations.Destinations
import com.jarica.chronogol.presentation.screens.oneplayerscreen.OnePlayerViewModel
import com.jarica.chronogol.presentation.screens.splashscreen.SplashScreenUi

@Composable
fun RootNavGraph(navController: NavHostController, OnePlayerViewModel: OnePlayerViewModel) {

    NavHost(
    navController = navController,
    route = ROOT_GRAPH,
    startDestination = Destinations.SplashScreen.route
    ) {

        composable(Destinations.SplashScreen.route) {
            SplashScreenUi(navController)
        }

        HomeNavGraph(navController = navController, OnePlayerViewModel)

    }
}


