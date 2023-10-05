package com.jarica.chronogol.presentation.screens.oneplayerscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jarica.chronogol.presentation.components.CircularProgressBar
import com.jarica.chronogol.presentation.ui.theme.AzulGradientClaro
import com.jarica.chronogol.presentation.ui.theme.AzulGradientOscuro
import com.jarica.chronogol.presentation.ui.theme.AzulOscuro
import com.jarica.chronogol.presentation.ui.theme.keepcalm
import com.jarica.chronogol.presentation.util.SquashedOvalDown

@Composable
fun OnePlayerScreenUi(navController: NavHostController, onePlayerViewModel: OnePlayerViewModel) {

    val totalTime: Long by onePlayerViewModel.totalTime.observeAsState(2000)
    val currentTime: Long by onePlayerViewModel.currentTime.observeAsState(0)
    val value: Float by onePlayerViewModel.value.observeAsState(0f)
    val size: IntSize by onePlayerViewModel.size.observeAsState(initial = IntSize.Zero )
    val isTimeRunning: Boolean by onePlayerViewModel.isTimerRunning.observeAsState(false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulOscuro)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderUi()
            BodyUi(navController, onePlayerViewModel, totalTime, currentTime, value, size, isTimeRunning)

        }

    }

}



@Composable
fun TextTime(currentTime: Long) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        Text(text = String.format("%02d", currentTime / 100), fontSize = 44.sp, color = Color.White)
        Text(text = ":", fontSize = 44.sp, color = Color.White)
        Text(text = String.format("%02d", currentTime % 100), fontSize = 75.sp, color = Color.White)
    }
}

@Composable
fun BodyUi(
    navController: NavHostController,
    onePlayerViewModel: OnePlayerViewModel,
    totalTime: Long,
    currentTime: Long,
    value: Float,
    size: IntSize,
    isTimeRunning: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                AzulOscuro
            )
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressBar(
            totalTime = totalTime,
            AzulGradientOscuro,
            Color.Gray,
            AzulGradientClaro,
            modifier = Modifier.size(250.dp),
            onePlayerViewModel,
            currentTime,
            value,
            size,
            isTimeRunning
        )
    }
}


@Composable
fun HeaderUi() {
    Column(
        modifier = Modifier
            .height(155.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AzulGradientClaro,
                        AzulGradientOscuro
                    )
                ), shape = SquashedOvalDown()
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "1 JUGADOR",
            fontFamily = keepcalm,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            color = Color.White,
        )
    }
}