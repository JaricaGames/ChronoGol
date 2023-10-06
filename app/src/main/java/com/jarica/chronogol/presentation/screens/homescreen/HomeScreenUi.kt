package com.jarica.chronogol.presentation.screens.homescreen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jarica.chronogol.R
import com.jarica.chronogol.presentation.components.CustomButton
import com.jarica.chronogol.presentation.navigation.destinations.Destinations
import com.jarica.chronogol.presentation.ui.theme.AzulGradientClaro
import com.jarica.chronogol.presentation.ui.theme.AzulGradientOscuro
import com.jarica.chronogol.presentation.ui.theme.AzulOscuro
import com.jarica.chronogol.presentation.ui.theme.PurpleGrey80
import com.jarica.chronogol.presentation.ui.theme.keepcalm
import com.jarica.chronogol.presentation.util.SquashedOvalTop

@Composable
fun HomeScreenUi(navController: NavHostController) {

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
            BodyUi(navController)

        }

    }

}

@Composable
fun BodyUi(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AzulGradientClaro,
                        AzulGradientOscuro
                    )
                ), shape = SquashedOvalTop()
            )
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {


        Image(
            painter = painterResource(id = R.drawable.sintitulo),
            contentDescription = "",
            modifier = Modifier.size(250.dp),
            contentScale = ContentScale.FillHeight,
        )

        Card(
            modifier = Modifier
                .height(200.dp),
            shape = RoundedCornerShape(25.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(0f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                CustomButton(
                    Modifier.fillMaxWidth(),
                    "1 JUGADOR",
                    R.drawable.soccer_ball_illustration_svgrepo_com
                ) { navController.navigate(Destinations.OnePlayerScreen.route) }

                CustomButton(
                    Modifier.fillMaxWidth(),
                    "Puntuaciones",
                    R.drawable.list_numbers_svgrepo_com
                ) { navController.navigate(Destinations.PuntuationScreen.route) }


            }


        }
        Spacer(modifier = Modifier.size(60.dp))
    }
}

@Composable
fun HeaderUi() {
    Column(
        modifier = Modifier.height(125.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "CHRONOGOL",
            fontFamily = keepcalm,
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp,
            color = AzulGradientClaro,
        )
    }
}
