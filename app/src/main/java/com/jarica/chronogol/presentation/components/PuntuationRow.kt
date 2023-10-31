package com.jarica.chronogol.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jarica.chronogol.domain.models.Puntuation
import com.jarica.chronogol.presentation.ui.theme.rajdhani

@Composable
fun PuntuationRow(item: Puntuation, position: Int, isshowposition: Boolean) {

    Row(
        Modifier
            .fillMaxWidth()
            .height(45.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        if (isshowposition) {
            Text(
                text = position.toString(),
                color = Color.White,
                modifier = Modifier.weight(0.05f),
                textAlign = TextAlign.Center,
                fontFamily = rajdhani,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )
        } else {
            Text(
                text = "-",
                color = Color.White,
                modifier = Modifier.weight(0.05f),
                textAlign = TextAlign.Center,
                fontFamily = rajdhani,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )
        }
        Text(
            text = item.name,
            color = Color.White,
            modifier = Modifier
                .weight(0.80f)
                .padding(horizontal = 16.dp),
            fontFamily = rajdhani,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = item.goals.toString(),
            color = Color.White,
            modifier = Modifier.weight(0.15f),
            textAlign = TextAlign.Center,
            fontFamily = rajdhani,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }

}