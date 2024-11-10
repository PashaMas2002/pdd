package com.example.composablepdd.screens.test.elements.test_end.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composablepdd.R
import com.example.composablepdd.ui.theme.ColorGradient1
import com.example.composablepdd.ui.theme.ColorGradient2
import com.example.composablepdd.ui.theme.ColorGradient3
import com.example.composablepdd.ui.theme.ColorGradient4

@Composable
fun TheEndButtonUpdateResult(
    onClick: () -> Unit,
) {
    val colorGr1 = ColorGradient1
    val colorGr2 = ColorGradient2
    val colorGr3 = ColorGradient3
    val colorGr4 = ColorGradient4

    val transition = rememberInfiniteTransition(label = "")
    val color1: Color by transition.animateColor(
        initialValue = colorGr1,
        targetValue = colorGr2,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5 * 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val color2: Color by transition.animateColor(
        initialValue = colorGr3,
        targetValue = colorGr4,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5 * 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val gradientBrush = Brush.linearGradient(
        colors = listOf(color1, color2)
    )
    val isVisible = remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = isVisible.value
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(top = 10.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = RoundedCornerShape(15.dp),
            onClick = {
                onClick()
                isVisible.value = false
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = gradientBrush),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Отметить как решенный без ошибок",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.font_main_bold)),
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}