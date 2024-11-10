package com.example.composablepdd.screens.test.elements.tab_padger.elements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composablepdd.ui.myTheme.AppTheme

@Composable
fun CustomProgressBarPager(
    color: Color,
    list: MutableList<MutableState<Int>>,
    appTheme: AppTheme
) {
    val foreground = appTheme.backgroundApp()

    val listProgress = list.filter { it.value != 0 }

    val progress = if (list.isNotEmpty()) {
        listProgress.size.toFloat() / list.size
    } else {
        0f
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
    ) {
        val strokeWidth = 16f
        val padding = 4f

        drawRoundRect(
            color = color.copy(alpha = 0.3f),
            topLeft = Offset(0f, size.height / 2 - strokeWidth / 2),
            size = size.copy(height = strokeWidth),
            cornerRadius = CornerRadius(10f, 10f)
        )
        drawRoundRect(
            color = color,
            topLeft = Offset(0f, size.height / 2 - strokeWidth / 2),
            size = size.copy(width = size.width * progress, height = strokeWidth),
            cornerRadius = CornerRadius(10f, 10f)
        )
        drawCircle(
            color = foreground,
            radius = strokeWidth / 1.6f + padding,
            center = Offset(size.width * progress, size.height / 2)
        )
        drawCircle(
            color = color,
            radius = strokeWidth / 3 + padding,
            center = Offset(size.width * progress, size.height / 2)
        )
    }
}