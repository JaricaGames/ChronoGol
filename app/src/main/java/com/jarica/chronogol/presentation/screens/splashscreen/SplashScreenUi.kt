package com.jarica.chronogol.presentation.screens.splashscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jarica.chronogol.presentation.navigation.destinations.Destinations
import com.jarica.chronogol.presentation.ui.theme.AzulGradientClaro
import com.jarica.chronogol.presentation.ui.theme.AzulGradientOscuro
import com.jarica.chronogol.presentation.ui.theme.AzulOscuro
import com.jarica.chronogol.presentation.ui.theme.rajdhani
import kotlinx.coroutines.delay


@Composable
fun SplashScreenUi(navController: NavHostController) {

    LaunchedEffect(key1 = true ){
        delay(2500L)
        navController.popBackStack()
        navController.navigate(Destinations.HomeScreen.route)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(AzulOscuro), contentAlignment = Alignment.Center)
    {
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "CHRONOGOL",
                fontFamily = rajdhani,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 55.sp,
                color = AzulGradientClaro,
            )
            Text(
                text = "by JARICA",
                fontFamily = rajdhani,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                color = AzulGradientClaro,
            )
        }


    }
}