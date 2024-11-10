package com.example.composablepdd.screens.main.elements

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composablepdd.R
import com.example.composablepdd.ui.myTheme.AppTheme

@Composable
fun MainTopMenu(
    appTheme: AppTheme,
    isVisible: MutableState<Boolean>,
    decreasingNum: Animatable<Float, AnimationVector1D>,
    increasingNum: Animatable<Float, AnimationVector1D>
){
    val textSize by animateFloatAsState(targetValue = 18 + (decreasingNum.value * 6), label = "")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height((80 + (20 * decreasingNum.value)).dp)
            .background(
                appTheme
                    .foregroundApp()
                    .copy(alpha = 0.98f * increasingNum.value)
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        if (!isVisible.value) {
            Text(
                text = "Билеты 2024",
                style = TextStyle(
                    fontSize = textSize.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
                color = appTheme.colorTextApp(),
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )
        }
    }
}