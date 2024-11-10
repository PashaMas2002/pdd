package com.example.composablepdd.screens.test.elements.tab_padger.elements

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composablepdd.R
import com.example.composablepdd.screens.test.func.MyCountDownTimer

@Composable
fun TextTimer(
    color2: Color,
    modifier: Modifier = Modifier,
    timeForTimer: Long,
    theEndTest: MutableState<Boolean>,
    timeUp: MutableState<Boolean>,
) {
    val countdownTimer = remember(timeForTimer) { MyCountDownTimer(timeForTimer) }

    LaunchedEffect(timeForTimer) {
        countdownTimer.start()
    }

    val timeRemaining = countdownTimer.timeRemaining.value
    val time = formatTime(timeRemaining)

    val isPulsing = timeRemaining <= 30
    val color by animateColorAsState(
        targetValue = if (isPulsing) Color.Red else color2,
        animationSpec = repeatable(
            iterations = if (isPulsing) Int.MAX_VALUE else 1,
            animation = tween(durationMillis = 1000)
        )
    )

    val scale by animateFloatAsState(
        targetValue = if (isPulsing) 1.1f else 1f,
        animationSpec = tween(durationMillis = 1000)
    )
    LaunchedEffect(timeRemaining) {
        if (timeRemaining == 0L) {
            countdownTimer.cancel()
            theEndTest.value = true
            timeUp.value = true
        }
    }
    AnimCount(
        time = time,
        color = color,
        color2 = color2,
        scale = scale,
        modifier = modifier
    )
}

private fun formatTime(seconds: Long): String {
    val minutes = seconds / 60
    val secs = seconds % 60
    return String.format("%02d:%02d", minutes, secs)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimCount(
    time: String,
    color: Color,
    color2: Color,
    scale: Float,
    modifier: Modifier = Modifier
) {

    val oldCount = remember {
        mutableStateOf(time)
    }
    SideEffect {
        oldCount.value = time
    }
    Row(
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_stopwatch),
            contentDescription = null,
            tint = color2,
            modifier = Modifier.size(16.dp)
        )
        Row(
            modifier = modifier.padding(bottom = 10.dp, end = 20.dp),
        ) {
            val countString = time
            val oldCountString = oldCount.toString()
            for (i in countString.indices) {
                val oldChar = oldCountString.getOrNull(i)
                val newChar = countString[i]
                val char = if (oldChar == newChar) {
                    oldCountString[i]
                } else {
                    countString[i]
                }
                AnimatedContent(
                    targetState = char,
                    transitionSpec = {
                        slideInVertically { it } with slideOutVertically { -it }
                    }, label = ""
                ) { textTime ->
                    Text(
                        text = textTime.toString(),
                        color = color,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.font_main_bold))
                        ),
                        modifier = modifier
                            .graphicsLayer(scaleX = scale, scaleY = scale)
                    )
                }
            }
        }
    }
}