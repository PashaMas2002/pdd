package com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composablepdd.R

@Composable
fun ButtonTabContent(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec = tween(500)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 5.dp,
                bottom = 5.dp,
                start = 20.dp,
                end = 20.dp
            ),
        colors = CardDefaults.cardColors(Color.White.copy(0.2f)),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        onClick = {
            onClick()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.3.dp)
                .background(
                    shape = RoundedCornerShape(15.dp),
                    color = animatedColor
                )
        ) {
            Text(
                text = text,
                color = Color.White,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.font_main)),
                ),
                modifier = Modifier
                    .padding(15.dp)
            )
        }

    }

}