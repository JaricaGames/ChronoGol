package com.jarica.chronogol.presentation.screens.splashscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.jarica.chronogol.presentation.navigation.destinations.Destinations

@Composable
fun SplashScreenUi(navController: NavHostController) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue)){
        Column {

            Text(text = "SpashScreen")
            Button(onClick = { navController.navigate(Destinations.HomeScreen.route)}) {
                Text(text = "pulsa")
            }
        }
    }
}