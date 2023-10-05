package com.jarica.chronogol.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jarica.chronogol.R

val sans = FontFamily(
    Font(R.font.sansvariable),
    Font(R.font.sansmedium, FontWeight.Medium),
    Font(R.font.sansextrabold, FontWeight.ExtraBold),
    Font(R.font.sanssemibold, FontWeight.SemiBold),
    Font(R.font.sansthin, FontWeight.Thin),
    Font(R.font.sansbold, FontWeight.Bold),
    Font(R.font.sanslight, FontWeight.Light),
    Font(R.font.sansregular, FontWeight.Normal)
)
val digital = FontFamily(
    Font(R.font.display, FontWeight.Normal)
)

val keepcalm = FontFamily(
    Font(R.font.keepcalmmedium, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)