package com.example.composablepdd.screens.settings

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.R
import com.example.composablepdd.screens.settings.elements.AppThemeSetting
import com.example.composablepdd.screens.settings.elements.Notification
import com.example.composablepdd.screens.settings.elements.PopUpReminder
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.screens.settings.elements.TopMenuSettings
import kotlin.math.max

@Composable
fun ScreenSettings(
    navController: NavController,
    activity: Activity,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val appTheme = mainViewModel.getAppTheme
    val outerListState = rememberLazyListState()
    val screenHeight = with(LocalDensity.current) { 200.dp.toPx() }
    val headerHeight = screenHeight / 2
    val decreasingNum = remember { Animatable(1f) }
    val increasingNum = remember { Animatable(1f) }

    LaunchedEffect(outerListState) {
        snapshotFlow { outerListState.firstVisibleItemScrollOffset }
            .collect { offset ->
                if (outerListState.firstVisibleItemIndex == 0) {
                    val newAlpha = max(1f - (offset / headerHeight * 2), 0f)
                    decreasingNum.snapTo(newAlpha)
                    increasingNum.snapTo(1f - newAlpha * 1.5f)
                } else {
                    decreasingNum.snapTo(0f)
                    increasingNum.snapTo(1f)
                }
            }
    }
    val backgroundColor by animateColorAsState(
        targetValue = appTheme.backgroundApp()
    )
    val isVisiblePopUpReminder = remember { mutableStateOf(false) }

    val blur = when {
        isVisiblePopUpReminder.value -> 30.dp
        else -> 0.dp
    }
    Box(
        modifier = Modifier.blur(blur)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .animateContentSize()
                .background(backgroundColor)
                .blur(blur),
            state = outerListState,
            contentPadding = PaddingValues(
                top = 120.dp,
                bottom = 90.dp
            )
        ) {
            item { AppThemeSetting(appTheme, mainViewModel) }
            item { Notification(appTheme, isVisiblePopUpReminder, mainViewModel) }
        }
        TopMenuSettings(
            appTheme = appTheme,
            decreasingNum = decreasingNum,
            increasingNum = increasingNum,
            navController = navController
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AnimatedVisibility(
            visible = isVisiblePopUpReminder.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            PopUpReminder(
                isVisible = isVisiblePopUpReminder,
                activity = activity,
                mainViewModel = mainViewModel,
            )
        }
    }
}

