package com.jarica.chronogol.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jarica.chronogol.presentation.ui.theme.Purple80
import com.jarica.chronogol.presentation.ui.theme.PurpleGrey80
import com.jarica.chronogol.presentation.ui.theme.keepcalm
import com.jarica.chronogol.presentation.ui.theme.rajdhani


@Composable
fun CustomButton(modifier: Modifier, textButton: String, icon: Int, onClick: () -> Unit){

    Button(
        onClick = {  onClick() },
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxWidth()

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "",
                Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.size(15.dp))
            Text(
                text = textButton,
                fontFamily = rajdhani,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = PurpleGrey80
            )
        }
    }
}
