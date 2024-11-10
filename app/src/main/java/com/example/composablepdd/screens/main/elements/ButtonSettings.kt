package com.example.composablepdd.screens.main.elements

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composablepdd.R
import com.example.composablepdd.ui.myTheme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun ButtonSettings(
    appTheme: AppTheme,
    navController: NavController
) {
    val rotationState = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val onClick = {
        scope.launch {
            rotationState.animateTo(
                targetValue = rotationState.value + 360f,
                animationSpec = tween(durationMillis = 300)
            )
            navController.navigate("SETTINGS_SCREEN")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(25.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_settings),
            contentDescription = null,
            tint = appTheme.colorIconApp(),
            modifier = Modifier
                .size(28.dp)
                .graphicsLayer(rotationZ = rotationState.value)
                .clickable(
                    onClick = { onClick() },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        )
    }

    LaunchedEffect(Unit) {
        while (true) {
            rotationState.animateTo(
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 10000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }
}