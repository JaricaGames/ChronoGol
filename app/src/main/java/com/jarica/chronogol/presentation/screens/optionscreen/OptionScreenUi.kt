package com.jarica.chronogol.presentation.screens.optionscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jarica.chronogol.presentation.screens.oneplayerscreen.OnePlayerViewModel
import com.jarica.chronogol.presentation.ui.theme.AzulGradientClaro
import com.jarica.chronogol.presentation.ui.theme.AzulGradientOscuro
import com.jarica.chronogol.presentation.ui.theme.AzulOscuro
import com.jarica.chronogol.presentation.ui.theme.Purple80
import com.jarica.chronogol.presentation.ui.theme.rajdhani
import com.jarica.chronogol.presentation.ui.theme.sans
import com.jarica.chronogol.presentation.util.SquashedOvalDown

@Composable
fun OptionScreenUi(
    navController: NavHostController,
    optionViewModel: OptionViewModel,
    onePlayerViewModel: OnePlayerViewModel
) {

    val gameDuration by optionViewModel.gameDuration.observeAsState(initial = 30)
    val isSoundActive by optionViewModel.isSoundActive.observeAsState(initial = true)
    val isMusicActive by optionViewModel.isMusicActive.observeAsState(initial = true)


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
            BodyUi(
                navController,
                gameDuration,
                optionViewModel,
                isSoundActive,
                isMusicActive,
                onePlayerViewModel
            )

        }

    }
}


@Composable
fun BodyUi(
    navController: NavHostController,
    gameDuration: Int,
    optionViewModel: OptionViewModel,
    isSoundActive: Boolean,
    isMusicActive: Boolean,
    onePlayerViewModel: OnePlayerViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                AzulOscuro
            )
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Duración del partido:",
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = rajdhani,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DurationGameCustomButton(
                duration = gameDuration,
                "30",
                Modifier.weight(1f),
                optionViewModel,
                onePlayerViewModel
            )
            DurationGameCustomButton(
                duration = gameDuration,
                "60",
                Modifier.weight(1f),
                optionViewModel,
                onePlayerViewModel
            )
            DurationGameCustomButton(
                duration = gameDuration,
                "90",
                Modifier.weight(1f),
                optionViewModel,
                onePlayerViewModel
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Sonidos:",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = rajdhani,
                fontWeight = FontWeight.Normal
            )
            Switch(
                checked = isSoundActive,
                onCheckedChange = {
                    optionViewModel.onSoundActiveChecked()
                },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = Purple80
                )
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Música:",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = rajdhani,
                fontWeight = FontWeight.Normal
            )
            Switch(
                checked = isMusicActive,
                onCheckedChange = { optionViewModel.onMusicActiveChecked() },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = Purple80
                )
            )
        }

    }
}

@Composable
fun HeaderUi() {
    Column(
        modifier = Modifier
            .height(200.dp)
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
            text = "Opciones",
            fontFamily = rajdhani,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 40.sp,
            color = AzulOscuro,
        )
    }
}


@Composable
fun DurationGameCustomButton(
    duration: Int,
    text: String,
    modifier: Modifier,
    optionViewModel: OptionViewModel,
    onePlayerViewModel: OnePlayerViewModel
) {

    if (duration.toString().equals(text)) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Purple80),
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            modifier = modifier
        ) {
            Text(text = "$text''", color = Color.White, fontSize = 22.sp, fontFamily = rajdhani)
        }
    } else {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Purple80.copy(0.08f)),
            onClick = {
                optionViewModel.changeDuration(text.toInt())
                onePlayerViewModel.changeDuration(text.toInt())
            },
            shape = RoundedCornerShape(0.dp),
            modifier = modifier
        ) {
            Text(text = "$text''", color = Color.White, fontSize = 20.sp, fontFamily = rajdhani)
        }
    }
}





