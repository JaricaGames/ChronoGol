package com.jarica.chronogol.presentation.screens.twoplayersscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun TwoPlayersScreenUi(navController: NavHostController){

    Box(modifier = Modifier.fillMaxSize() ){
        Text(text = "TwoPlayersScreen")
    }
}