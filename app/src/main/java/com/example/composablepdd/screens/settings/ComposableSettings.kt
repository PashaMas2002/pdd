package com.example.composablepdd.screens.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.screens.settings.elements.TopMenuSettings
import kotlin.math.max

@Composable
fun ScreenSettings(
    navController: NavController,
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        state = outerListState,
        contentPadding = PaddingValues(
            top = 120.dp,
            bottom = 90.dp
        )
    ) {
        item { AppThemeSetting(appTheme, mainViewModel) }
    }
    TopMenuSettings(
        appTheme = appTheme,
        decreasingNum = decreasingNum,
        increasingNum = increasingNum,
        navController = navController
    )
}

@Composable
fun AppThemeSetting(
    appTheme: AppTheme,
    mainViewModel: MainViewModel
) {
    val getSaveValueSettings = mainViewModel.getSaveValueSettings

    val isSystemTheme = remember { getSaveValueSettings.systemTheme }
    LaunchedEffect(isSystemTheme.value) {
        getSaveValueSettings.saveSystemTheme(isSystemTheme.value)
    }

    val isDarkMode = remember { getSaveValueSettings.darkMode }
    LaunchedEffect(isDarkMode.value) {
        getSaveValueSettings.saveDarkMode(isDarkMode.value)
    }

    val icon = if(isSystemTheme.value){
        R.drawable.ic_auto_theme
    } else {
        if(isDarkMode.value){
            R.drawable.ic_dark_theme
        }else {
            R.drawable.ic_light_theme
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Светлая / Темная",
                color = appTheme.colorTextApp(),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
                modifier = Modifier
                    .padding(end = 8.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_circle_24),
                contentDescription = null,
                tint = appTheme.colorIconApp(),
                modifier = Modifier
                    .size(14.dp)
                    .padding(end = 8.dp)
            )
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = appTheme.colorIconApp(),
                modifier = Modifier
                    .size(18.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 30.dp,
                    end = 20.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Системная тема",
                color = appTheme.colorTextApp(),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
                modifier = Modifier
                    .padding(end = 8.dp)
            )
            Switch(
                checked = isSystemTheme.value,
                onCheckedChange = { isSystemTheme.value = it }
            )
        }
        AnimatedVisibility(
            visible = !isSystemTheme.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 30.dp,
                        end = 20.dp,
                        bottom = 15.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Темный режим",
                    color = appTheme.colorTextApp(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.font_main_bold)),
                    ),
                    modifier = Modifier
                        .padding(end = 8.dp)
                )
                Switch(
                    checked = isDarkMode.value,
                    onCheckedChange = { isDarkMode.value = it }
                )
            }
        }
    }
}
