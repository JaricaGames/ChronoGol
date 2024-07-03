package com.jarica.chronogol.presentation.screens.puntuations

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jarica.chronogol.domain.models.Puntuation
import com.jarica.chronogol.presentation.components.PuntuationRow
import com.jarica.chronogol.presentation.navigation.destinations.Destinations
import com.jarica.chronogol.presentation.ui.theme.AzulGradientClaro
import com.jarica.chronogol.presentation.ui.theme.AzulGradientOscuro
import com.jarica.chronogol.presentation.ui.theme.AzulOscuro
import com.jarica.chronogol.presentation.ui.theme.Purple80
import com.jarica.chronogol.presentation.ui.theme.rajdhani


@Composable
fun PuntuationScreenUi(navController: NavHostController, puntuationViewModel: PuntuationViewModel) {


    val puntuationListByOrder =
        puntuationViewModel.puntuationListByOrder.collectAsState(initial = listOf()).value
    val gameDuration by puntuationViewModel.gameDuration.observeAsState(initial = 30)

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
            HeaderUi(gameDuration, puntuationViewModel, navController)
            BodyUi(navController, puntuationViewModel, puntuationListByOrder!!)

        }

    }
}


@Composable
fun BodyUi(
    navController: NavHostController,
    puntuationViewModel: PuntuationViewModel,
    puntuationListByOrder: List<Puntuation>
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
        Spacer(modifier = Modifier.size(24.dp))
        HeaderLazyColum()
        Spacer(modifier = Modifier.size(8.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            var goalsAux = 0
            var position = 0
            var isshowposition: Boolean

            items(puntuationListByOrder) { item ->
                if (item.goals != goalsAux) {
                    position += 1
                    goalsAux = item.goals
                    isshowposition = true
                } else {
                    position += 1
                    isshowposition = false
                }
                PuntuationRow(item, position, isshowposition)
            }
        }

    }
}

@Composable
fun HeaderLazyColum() {
    Row(
        Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "", modifier = Modifier
                .weight(0.05f)
        )
        Text(
            text = "NOMBRE",
            modifier = Modifier
                .weight(0.80f)
                .padding(horizontal = 16.dp),
            color = Purple80,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            fontFamily = rajdhani
        )
        Text(
            text = "GOALS",
            modifier = Modifier.weight(0.15f),
            color = Purple80,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontFamily = rajdhani
        )

    }
}


@Composable
fun HeaderUi(
    gameDuration: Int,
    puntuationViewModel: PuntuationViewModel,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .height(150.dp)
            .offset(0.dp, 5.dp)
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AzulGradientClaro,
                        AzulGradientOscuro
                    )
                )
            ),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "",
            Modifier
                .size(25.dp)
                .clickable {
                    navController.navigate(
                        Destinations.HomeScreen.route
                    )
                },
            tint = AzulOscuro
        )
        Text(
            text = "Puntuaciones",
            fontFamily = rajdhani,
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color = AzulOscuro,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
        Spacer(modifier = Modifier.size(18.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DurationGameCustomButton(
                duration = gameDuration,
                text = "30",
                Modifier.weight(1f),
                puntuationViewModel = puntuationViewModel
            ) { puntuationViewModel.getPuntuations30ByOrder() }
            DurationGameCustomButton(
                duration = gameDuration,
                text = "60",
                Modifier.weight(1f),
                puntuationViewModel = puntuationViewModel,
            ) { puntuationViewModel.getPuntuations60ByOrder() }
            DurationGameCustomButton(
                duration = gameDuration,
                text = "90",
                Modifier.weight(1f),
                puntuationViewModel = puntuationViewModel,
            ) { puntuationViewModel.getPuntuations90ByOrder() }
        }
    }
}

@Composable
fun DurationGameCustomButton(
    duration: Int,
    text: String,
    modifier: Modifier,
    puntuationViewModel: PuntuationViewModel,
    onClick: () -> Unit,
) {

    if (duration.toString().equals(text)) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Purple80),
            onClick = {},
            shape = RoundedCornerShape(0.dp),
            modifier = modifier,
        ) {
            Text(text = "$text''", color = AzulOscuro, fontSize = 22.sp, fontFamily = rajdhani)
        }
    } else {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Purple80.copy(0f)),
            onClick = {
                puntuationViewModel.changeDuration(text.toInt(), onClick)
            },
            shape = RoundedCornerShape(0.dp),
            modifier = modifier
        ) {
            Text(text = "$text''", color = Color.White, fontSize = 20.sp, fontFamily = rajdhani)
        }
    }
}

