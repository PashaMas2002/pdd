package com.example.composablepdd.screens.settings.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composablepdd.R
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.ui.myTheme.AppTheme

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

    val icon = if (isSystemTheme.value) {
        R.drawable.ic_auto_theme
    } else {
        if (isDarkMode.value) {
            R.drawable.ic_dark_theme
        } else {
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
        Column(
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                )
                .border(
                    .3.dp,
                    appTheme
                        .colorTextApp()
                        .copy(.3f),
                    RoundedCornerShape(15.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 10.dp),
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
                            start = 20.dp,
                            end = 10.dp
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
}
