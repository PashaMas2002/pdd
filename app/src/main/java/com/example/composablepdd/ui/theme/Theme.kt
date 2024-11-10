package com.example.composablepdd.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composablepdd.application.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Tertiary,
    onPrimary = OnPrimary,
)

@Composable
fun ComposablePDDTheme(
    mainViewModel: MainViewModel = hiltViewModel(),
    content: @Composable () -> Unit,
) {
    val isDarkIcon = mainViewModel.getAppTheme.isDarkIconStatusBar()
    val colorScheme = DarkColorScheme
    val systemUiController = rememberSystemUiController()
    systemUiController.setNavigationBarColor(Color.Transparent)
    systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = isDarkIcon)
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}