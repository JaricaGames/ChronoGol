package com.jarica.chronogol.presentation.navigation.destinations

sealed class Destinations (val route:String){

    object SplashScreen :Destinations(
        route = "SplashScreen"
    )

    object HomeScreen :Destinations(
        route = "HomeScreen"
    )

    object OnePlayerScreen :Destinations(
        route = "OnePlayerScreen"
    )

    object TwoPlayerScreen :Destinations(
        route = "TwoPlayerScreen"
    )

    object PuntuationScreen :Destinations(
        route = "PuntuationScreen"
    )

    object OptionScreen :Destinations(
        route = "OptionScreen"
    )
}
