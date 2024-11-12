package com.example.composablepdd.screens.settings.elements

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composablepdd.R
import com.example.composablepdd.ui.myTheme.AppTheme

@Composable
fun TopMenuSettings(
    appTheme: AppTheme,
    decreasingNum: Animatable<Float, AnimationVector1D>,
    increasingNum: Animatable<Float, AnimationVector1D>,
    navController: NavController
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
        contentAlignment = Alignment.BottomCenter,

    ) {
        Box(
            modifier = Modifier
                .padding(start = 20.dp, bottom = 10.dp)
                .size(textSize.dp)
                .clip(CircleShape)
                .align(Alignment.BottomStart)
                .background(
                    appTheme
                        .colorIconApp()
                        .copy(.75f)
                )
                .clickable {
                    navController.navigate("MAIN_SCREEN") {
                        popUpTo("MAIN_SCREEN") { inclusive = true }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_exit),
                contentDescription = null,
                tint = appTheme.foregroundApp(),
                modifier = Modifier.fillMaxSize(.8f)
            )
        }
        Text(
            text = "Настройки",
            color = appTheme.colorTextApp(),
            style = TextStyle(
                fontSize = textSize.sp,
                fontFamily = FontFamily(Font(R.font.font_main_bold)),
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp)
        )
    }
}