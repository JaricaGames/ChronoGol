package com.jarica.chronogol.presentation.util

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class SquashedOvalDown : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {

            addOval(
                Rect(
                    left = -size.width ,
                    top = -size.height*2f,
                    right = size.width*2f,
                    bottom = size.height
                )
            )
        }
        return Outline.Generic(path = path)
    }
}