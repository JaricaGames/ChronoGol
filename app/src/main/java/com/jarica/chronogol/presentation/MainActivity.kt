package com.jarica.chronogol.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jarica.chronogol.presentation.navigation.graph.RootNavGraph
import com.jarica.chronogol.presentation.screens.oneplayerscreen.OnePlayerViewModel
import com.jarica.chronogol.presentation.screens.optionscreen.OptionViewModel
import com.jarica.chronogol.presentation.screens.puntuations.PuntuationViewModel
import com.jarica.chronogol.presentation.ui.theme.ChronoGolTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val onePlayerViewModel: OnePlayerViewModel by viewModels()
    private val optionViewModel: OptionViewModel by viewModels()
    private val puntuationViewModel: PuntuationViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false) // para quitar color a la barra de estado de ANDROID

        setContent {
            ChronoGolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    RootNavGraph(navController = navController, onePlayerViewModel, optionViewModel, puntuationViewModel)
                }
            }
        }
    }
}
