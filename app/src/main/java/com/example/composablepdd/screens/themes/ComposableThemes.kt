package com.example.composablepdd.screens.themes

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.screens.themes.elements.ItemThemes
import com.example.composablepdd.screens.themes.elements.ThemesTopMenu
import kotlin.math.max

@Composable
fun ScreenThemes(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
){
    val appTheme = mainViewModel.getAppTheme

    val (themesProgress, themesSize) =
        mainViewModel.getTableThemes.getProgressThemes()
    
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

    val listThemes = mainViewModel.getTableThemes.getItemThemes()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(appTheme.backgroundApp()),
        state = outerListState,
        contentPadding = PaddingValues(
            start = 20.dp,
            end = 20.dp,
            top = 120.dp,
            bottom = 90.dp
        )
    ) {
        itemsIndexed(listThemes) { index, item ->
            if (item != null) {
                ItemThemes(
                    index = index,
                    item = item,
                    navController = navController,
                )
            }
        }
    }
    ThemesTopMenu(
        appTheme = appTheme,
        decreasingNum = decreasingNum,
        increasingNum = increasingNum,
        themesProgress = themesProgress,
        themesSize = themesSize,
        navController = navController
    )
}
