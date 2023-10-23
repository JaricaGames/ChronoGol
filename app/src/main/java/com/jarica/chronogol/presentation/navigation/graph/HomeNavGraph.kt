package com.jarica.chronogol.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.jarica.chronogol.presentation.navigation.Graph.HOME_GRAPH
import com.jarica.chronogol.presentation.navigation.destinations.Destinations
import com.jarica.chronogol.presentation.screens.homescreen.HomeScreenUi
import com.jarica.chronogol.presentation.screens.oneplayerscreen.OnePlayerScreenUi
import com.jarica.chronogol.presentation.screens.oneplayerscreen.OnePlayerViewModel
import com.jarica.chronogol.presentation.screens.optionscreen.OptionScreenUi
import com.jarica.chronogol.presentation.screens.optionscreen.OptionViewModel
import com.jarica.chronogol.presentation.screens.puntuations.PuntuationScreenUi
import com.jarica.chronogol.presentation.screens.twoplayersscreen.TwoPlayersScreenUi


fun NavGraphBuilder.HomeNavGraph(
    navController: NavHostController,
    OnePlayerViewModel: OnePlayerViewModel,
    OptionViewModel: OptionViewModel
) {
    navigation(
        route = HOME_GRAPH,
        startDestination = Destinations.HomeScreen.route
    ) {
        composable(route = Destinations.HomeScreen.route) {
            HomeScreenUi(navController = navController)
        }
        composable(route = Destinations.OnePlayerScreen.route) {
            OnePlayerScreenUi(navController = navController, onePlayerViewModel = OnePlayerViewModel, OptionViewModel)
        }
        composable(route = Destinations.TwoPlayerScreen.route) {
            TwoPlayersScreenUi(navController = navController)
        }
        composable(route = Destinations.PuntuationScreen.route) {
            PuntuationScreenUi(navController = navController)
        }
        composable(route = Destinations.OptionScreen.route) {
            OptionScreenUi(navController = navController, OptionViewModel, OnePlayerViewModel)
        }
    }
}