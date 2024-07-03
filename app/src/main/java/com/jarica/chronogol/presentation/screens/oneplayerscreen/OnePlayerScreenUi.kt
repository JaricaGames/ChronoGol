package com.jarica.chronogol.presentation.screens.oneplayerscreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jarica.chronogol.R
import com.jarica.chronogol.data.model.PuntuationDto
import com.jarica.chronogol.presentation.components.CircularProgressBar
import com.jarica.chronogol.presentation.navigation.destinations.Destinations
import com.jarica.chronogol.presentation.screens.optionscreen.OptionViewModel
import com.jarica.chronogol.presentation.ui.theme.AzulGradientClaro
import com.jarica.chronogol.presentation.ui.theme.AzulGradientOscuro
import com.jarica.chronogol.presentation.ui.theme.AzulOscuro
import com.jarica.chronogol.presentation.ui.theme.Purple80
import com.jarica.chronogol.presentation.ui.theme.rajdhani
import com.jarica.chronogol.presentation.util.SquashedOvalDown

@Composable
fun OnePlayerScreenUi(
    navController: NavHostController,
    onePlayerViewModel: OnePlayerViewModel,
    optionViewModel: OptionViewModel
) {

    val adStattus: Boolean by onePlayerViewModel.adStatus.observeAsState(false)
    val context = LocalContext.current

    val totalTime: Int by optionViewModel.gameDuration.observeAsState(3000)
    val goals: Int by onePlayerViewModel.goals.observeAsState(0)
    val currentTime: Int by onePlayerViewModel.currentTime.observeAsState(3000)
    val value: Float by onePlayerViewModel.value.observeAsState(0f)
    val sizeCircularProgress: IntSize by onePlayerViewModel.sizeCircularProgress.observeAsState(
        initial = IntSize.Zero
    )
    val isTimeRunning: Boolean by onePlayerViewModel.isTimerRunning.observeAsState(false)
    val isPenaltyActive: Boolean by onePlayerViewModel.isPenaltyActive.observeAsState(false)
    val isGameFinish: Boolean by onePlayerViewModel.isGameFinished.observeAsState(false)
    val playerName: String by onePlayerViewModel.playerName.observeAsState("")

    //Variables Anim
    val goalAnimation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animationgol))
    val isPlayingGoalAnimation by onePlayerViewModel.isGoalAnimationActive.observeAsState(initial = false)
    val whistleAnimation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animationwhistle))
    val isPlayingWhistleAnimation by onePlayerViewModel.isWhistleAnimationActive.observeAsState(
        initial = false
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulOscuro)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderUi(totalTime, navController, onePlayerViewModel)
            if (!isPlayingGoalAnimation && !isPlayingWhistleAnimation) {
                BodyUi(
                    onePlayerViewModel,
                    totalTime,
                    currentTime,
                    value,
                    sizeCircularProgress,
                    isTimeRunning,
                    goals,
                    isPenaltyActive,
                    adStattus,
                    context
                )
            } else if (isPlayingGoalAnimation) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 25.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    LottieAnimation(
                        composition = goalAnimation,
                        modifier = Modifier.size(450.dp)
                    )
                }

            } else if (isPlayingWhistleAnimation) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 25.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    LottieAnimation(
                        composition = whistleAnimation,
                        modifier = Modifier.size(250.dp)
                    )
                }
            }
        }

    }

    if (isGameFinish) {
        GameFinished(onePlayerViewModel, navController, totalTime, goals, playerName)
    }

}

@Composable
fun GameFinished(
    onePlayerViewModel: OnePlayerViewModel,
    navController: NavHostController,
    totalTime: Int,
    goals: Int,
    playerName: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.65f))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(32.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            AzulGradientClaro,
                            AzulGradientOscuro
                        )
                    )
                )
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    Modifier
                        .size(35.dp)
                        .clickable { navController.navigate(Destinations.HomeScreen.route) },
                    tint = AzulOscuro
                )
                Text(text = "Partido a $totalTime''", color = AzulOscuro)
                Icon(
                    imageVector = Icons.Default.Refresh, contentDescription = "",
                    Modifier
                        .size(35.dp)
                        .clickable { onePlayerViewModel.restartGame() }, tint = AzulOscuro
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Goles marcados: ",
                    color = AzulOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    fontFamily = rajdhani,
                )
                Text(
                    text = "$goals ",
                    color = Purple80,
                    fontFamily = rajdhani,
                    fontSize = 54.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = playerName,
                onValueChange = { onePlayerViewModel.onValueChanged(it) },
                placeholder = {
                    Text(
                        text = "Introduce tu nombre",
                        color = AzulOscuro,
                        fontFamily = rajdhani,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                },
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )
            Spacer(modifier = Modifier.size(32.dp))
            Box(
                modifier = Modifier
                    .clickable {
                        if (goals != 0) {
                            onePlayerViewModel.savePuntuation(
                                PuntuationDto(
                                    name = playerName,
                                    goals = goals
                                )
                            )
                        }
                    }
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                AzulGradientClaro,
                                AzulGradientOscuro
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    fontWeight = FontWeight.Bold,
                    text = "Guardar Puntuación",
                    fontSize = 18.sp,
                    fontFamily = rajdhani,
                    color = AzulOscuro
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun TextTime(currentTime: Int) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = String.format("%02d", currentTime / 100),
            fontSize = 44.sp,
            color = Color.White,
            fontFamily = rajdhani,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = ":", fontSize = 44.sp, color = Color.White, fontFamily = rajdhani,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = String.format("%02d", currentTime % 100),
            fontSize = 75.sp,
            color = Color.White,
            fontFamily = rajdhani,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun BodyUi(
    onePlayerViewModel: OnePlayerViewModel,
    totalTime: Int,
    currentTime: Int,
    value: Float,
    size: IntSize,
    isTimeRunning: Boolean,
    goals: Int,
    isPenaltyActive: Boolean,
    adStatus: Boolean,
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                AzulOscuro
            )
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp),
            contentAlignment = Alignment.Center
        ) {
            PenaltyText(isPenaltyActive)
        }
        Spacer(modifier = Modifier.height(15.dp))
        CircularProgressBar(
            totalTime = totalTime,
            Purple80,
            Color.Gray,
            AzulGradientClaro,
            modifier = Modifier
                .size(250.dp),
            onePlayerViewModel,
            currentTime,
            value,
            size,
            isTimeRunning
        )
        ButtonPlayPause(onePlayerViewModel, isTimeRunning)
        Goals(goals, Modifier.offset(0.dp, (-60).dp))
/*        PenaltyButton(
            Modifier
                .align(Alignment.End)
                .offset(0.dp, (-50).dp),
            onePlayerViewModel = onePlayerViewModel, adStatus = adStatus, context
        )
    }*/
}

/*
@Composable
fun PenaltyButton(
    modifier: Modifier,
    onePlayerViewModel: OnePlayerViewModel,
    adStatus: Boolean,
    context: Context
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Tira un Superpenalty  -->",
            fontFamily = rajdhani,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
        Spacer(modifier = Modifier.size(18.dp))
        Box(
            modifier = Modifier
                .clickable {
                    if (adStatus) {
                    } else {
                        onePlayerViewModel.loadIntersticialAd({},context)
                    }
                }
                .size(100.dp, 40.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(
                    brush = Brush.verticalGradient(

                        colors = listOf(
                            AzulGradientClaro,
                            AzulGradientOscuro
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Ad",
                fontSize = 22.sp,
                fontFamily = rajdhani,
                color = Color.White
            )
        }
    }*/
}


@Composable
fun PenaltyText(isPenaltyActive: Boolean) {
    if (isPenaltyActive) {
        Text(
            text = "PENALTY !!!!!!!!!!",
            fontFamily = rajdhani,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Color.White,
        )
    }
}

@Composable
fun Goals(goals: Int, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Goals: ",
            fontFamily = rajdhani,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = goals.toString(),
            fontFamily = rajdhani,
            fontWeight = FontWeight.Bold,
            fontSize = 45.sp,
            color = Purple80
        )

    }
}


@Composable
fun HeaderUi(
    totalTime: Int,
    navController: NavHostController,
    onePlayerViewModel: OnePlayerViewModel
) {
    Column(
        modifier = Modifier
            .height(105.dp)
            .padding(top = 18.dp)
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
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                Modifier
                    .size(35.dp)
                    .clickable { navController.navigate(Destinations.HomeScreen.route) },
                tint = AzulOscuro
            )
            Text(
                text = "Partido a $totalTime''",
                fontFamily = rajdhani,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = AzulOscuro,
            )
            Icon(
                imageVector = Icons.Default.Refresh, contentDescription = "",
                Modifier
                    .size(35.dp)
                    .clickable { onePlayerViewModel.restartGame() }, tint = AzulOscuro
            )
        }
    }
}

@Composable
fun ButtonPlayPause(
    onePlayerViewModel: OnePlayerViewModel,
    isTimeRunning: Boolean
) {
    if (isTimeRunning) {
        Box(
            modifier = Modifier
                .offset(0.dp, (-75).dp)
                .size(110.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            AzulGradientClaro,
                            AzulGradientOscuro
                        )
                    )
                )
                .clickable { onePlayerViewModel.playPauseClicked() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painterResource(id = R.drawable.baseline_pause_24),
                contentDescription = "",
                modifier = Modifier.size(100.dp),
                tint = Color.White
            )
        }
    } else {
        Box(
            modifier = Modifier
                .offset(0.dp, -75.dp)
                .size(110.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            AzulGradientClaro,
                            AzulGradientOscuro
                        )
                    )
                )
                .clickable { onePlayerViewModel.playPauseClicked() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = "",
                modifier = Modifier.size(75.dp),
                tint = Color.White
            )
        }
    }


}
