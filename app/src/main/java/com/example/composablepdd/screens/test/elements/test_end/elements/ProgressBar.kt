package com.example.composablepdd.screens.test.elements.test_end.elements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composablepdd.ui.theme.ColorLightRed

@Composable
fun CustomProgressBarTheEnd(progress1: Float, progress2: Float, color: Color) {

    Canvas(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(31.dp)
    ) {
        val strokeWidth = 30f
        drawRoundRect(
            color = Color.Black,
            topLeft = Offset(0f, size.height / 2 - 31f / 2),
            size = size.copy(width = size.width, height = 31f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(15f, 15f)
        )
        drawRoundRect(
            color = ColorLightRed,
            topLeft = Offset(0f, size.height / 2 - strokeWidth / 2),
            size = size.copy(width = size.width * progress2, height = strokeWidth),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(15f, 15f)
        )

        drawRoundRect(
            color = color,
            topLeft = Offset(0f, size.height / 2 - strokeWidth / 2),
            size = size.copy(width = size.width * progress1, height = strokeWidth),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(15f, 15f)
        )
    }
}