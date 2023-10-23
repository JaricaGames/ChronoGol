package com.jarica.chronogol.presentation.screens.oneplayerscreen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.jarica.chronogol.presentation.components.CircularProgressBar
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

    val totalTime: Int by optionViewModel.gameDuration.observeAsState(3000)
    val goals: Int by onePlayerViewModel.goals.observeAsState(0)
    val currentTime: Int by onePlayerViewModel.currentTime.observeAsState(3000)
    val value: Float by onePlayerViewModel.value.observeAsState(0f)
    val sizeCircularProgress: IntSize by onePlayerViewModel.sizeCircularProgress.observeAsState(
        initial = IntSize.Zero
    )
    val isTimeRunning: Boolean by onePlayerViewModel.isTimerRunning.observeAsState(false)
    val isPenaltyActive: Boolean by onePlayerViewModel.isPenaltyActive.observeAsState(false)

    //Variables Anim
    val goalAnimation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animationgol))
    val isPlayingGoalAnimation by onePlayerViewModel.isGoalAnimationActive.observeAsState(initial = false)
    val whistleAnimation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animationwhistle))
    val isPlayingWhistleAnimation by onePlayerViewModel.isWhistleAnimationActive.observeAsState(initial = false)


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
            HeaderUi(totalTime)
            if (!isPlayingGoalAnimation && !isPlayingWhistleAnimation) {
                BodyUi(
                    navController,
                    onePlayerViewModel,
                    totalTime,
                    currentTime,
                    value,
                    sizeCircularProgress,
                    isTimeRunning,
                    goals,
                    isPenaltyActive
                )
            } else if(isPlayingGoalAnimation){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 25.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    LottieAnimation(composition = goalAnimation, modifier = Modifier.size(450.dp))
                }

            }else if(isPlayingWhistleAnimation){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 25.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    LottieAnimation(composition = whistleAnimation, modifier = Modifier.size(250.dp))
                }
            }

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
    navController: NavHostController,
    onePlayerViewModel: OnePlayerViewModel,
    totalTime: Int,
    currentTime: Int,
    value: Float,
    size: IntSize,
    isTimeRunning: Boolean,
    goals: Int,
    isPenaltyActive: Boolean
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
        Spacer(modifier = Modifier.height(25.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp),
            contentAlignment = Alignment.Center
        ) {
            PenaltyText(isPenaltyActive)
        }
        Spacer(modifier = Modifier.height(25.dp))
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
        Goals(goals, Modifier.offset(0.dp, -60.dp))
    }
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
            color = Color.White
        )

    }
}


@Composable
fun HeaderUi(totalTime: Int) {
    Column(
        modifier = Modifier
            .height(105.dp)
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
            text = "Partido a $totalTime''",
            fontFamily = rajdhani,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = Color.White,
        )
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
