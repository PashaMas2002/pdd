package com.example.composablepdd.ui.myTheme

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.example.composablepdd.database.preferences.SaveValueSettings
import com.example.composablepdd.ui.theme.BackgroundColorDark
import com.example.composablepdd.ui.theme.BackgroundColorLight
import com.example.composablepdd.ui.theme.ColorBottomButtonsExamDark
import com.example.composablepdd.ui.theme.ColorBottomButtonsExamLight
import com.example.composablepdd.ui.theme.ColorButtonPopUpWindowDark
import com.example.composablepdd.ui.theme.ColorButtonPopUpWindowLight
import com.example.composablepdd.ui.theme.ColorIconDark
import com.example.composablepdd.ui.theme.ColorIconLight
import com.example.composablepdd.ui.theme.ColorMainTopCardDark
import com.example.composablepdd.ui.theme.ColorMainTopCardLight
import com.example.composablepdd.ui.theme.ColorPopUpWindowDark
import com.example.composablepdd.ui.theme.ColorPopUpWindowLight
import com.example.composablepdd.ui.theme.ColorTextDark
import com.example.composablepdd.ui.theme.ColorTextLight

class AppTheme(
    private val setThemeSettings: SaveValueSettings
) {
    private val isSystemTheme = setThemeSettings.systemTheme
    private val isDarkMode = setThemeSettings.darkMode

    @Composable
    fun isDarkIconStatusBar(): Boolean {
        val isSystemDarkMode = isSystemInDarkTheme()
        return when {
            isSystemTheme.value && isSystemDarkMode -> false
            isSystemTheme.value && !isSystemDarkMode -> true
            !isSystemTheme.value && isDarkMode.value -> false
            !isSystemTheme.value && !isDarkMode.value -> true
            else -> false
        }
    }
    @Composable
    fun backgroundApp(): Color {
        val isSystemDarkMode = isSystemInDarkTheme()
        return when {
            isSystemTheme.value && isSystemDarkMode -> BackgroundColorDark
            isSystemTheme.value && !isSystemDarkMode -> BackgroundColorLight
            !isSystemTheme.value && isDarkMode.value -> BackgroundColorDark
            !isSystemTheme.value && !isDarkMode.value -> BackgroundColorLight
            else -> BackgroundColorLight
        }
    }
    @Composable
    fun backgroundBlurApp(): Color {
        val isSystemDarkMode = isSystemInDarkTheme()
        return when {
            isSystemTheme.value && isSystemDarkMode -> ColorPopUpWindowDark
            isSystemTheme.value && !isSystemDarkMode -> ColorPopUpWindowLight
            !isSystemTheme.value && isDarkMode.value -> ColorPopUpWindowDark
            !isSystemTheme.value && !isDarkMode.value -> ColorPopUpWindowLight
            else -> ColorPopUpWindowLight
        }
    }

    @Composable
    fun tabColorApp(): Color {
        val isSystemDarkMode = isSystemInDarkTheme()
        return when {
            isSystemTheme.value && isSystemDarkMode -> Color.Black
            isSystemTheme.value && !isSystemDarkMode -> Color.White
            !isSystemTheme.value && isDarkMode.value -> Color.Black
            !isSystemTheme.value && !isDarkMode.value -> Color.White
            else -> Color.White
        }
    }
    @Composable
    fun foregroundApp(): Color {
        val isSystemDarkMode = isSystemInDarkTheme()
        return when {
            isSystemTheme.value && isSystemDarkMode -> ColorMainTopCardDark
            isSystemTheme.value && !isSystemDarkMode -> ColorMainTopCardLight
            !isSystemTheme.value && isDarkMode.value -> ColorMainTopCardDark
            !isSystemTheme.value && !isDarkMode.value -> ColorMainTopCardLight
            else -> ColorMainTopCardLight
        }
    }

    @Composable
    fun colorTextApp(): Color {
        val isSystemDarkMode = isSystemInDarkTheme()
        return when {
            isSystemTheme.value && isSystemDarkMode -> ColorTextDark
            isSystemTheme.value && !isSystemDarkMode -> ColorTextLight
            !isSystemTheme.value && isDarkMode.value -> ColorTextDark
            !isSystemTheme.value && !isDarkMode.value -> ColorTextLight
            else -> ColorTextLight
        }
    }
    @Composable
    fun colorIconApp(): Color {
        val isSystemDarkMode = isSystemInDarkTheme()
        return when {
            isSystemTheme.value && isSystemDarkMode -> ColorIconDark
            isSystemTheme.value && !isSystemDarkMode -> ColorIconLight
            !isSystemTheme.value && isDarkMode.value -> ColorIconDark
            !isSystemTheme.value && !isDarkMode.value -> ColorIconLight
            else -> ColorIconLight
        }
    }
    @Composable
    fun colorTabExam(): Color {
        val isSystemDarkMode = isSystemInDarkTheme()
        return when {
            isSystemTheme.value && isSystemDarkMode -> ColorBottomButtonsExamDark
            isSystemTheme.value && !isSystemDarkMode -> ColorBottomButtonsExamLight
            !isSystemTheme.value && isDarkMode.value -> ColorBottomButtonsExamDark
            !isSystemTheme.value && !isDarkMode.value -> ColorBottomButtonsExamLight
            else -> ColorBottomButtonsExamLight
        }
    }

    @Composable
    fun colorPopUpWindow(): Color {
        val isSystemDarkMode = isSystemInDarkTheme()
        return when {
            isSystemTheme.value && isSystemDarkMode -> ColorPopUpWindowDark
            isSystemTheme.value && !isSystemDarkMode -> ColorPopUpWindowLight
            !isSystemTheme.value && isDarkMode.value -> ColorPopUpWindowDark
            !isSystemTheme.value && !isDarkMode.value -> ColorPopUpWindowLight
            else -> ColorPopUpWindowLight
        }
    }

    @Composable
    fun colorButtonPopUpWindow(): Color {
        val isSystemDarkMode = isSystemInDarkTheme()
        return when {
            isSystemTheme.value && isSystemDarkMode -> ColorButtonPopUpWindowDark
            isSystemTheme.value && !isSystemDarkMode -> ColorButtonPopUpWindowLight
            !isSystemTheme.value && isDarkMode.value -> ColorButtonPopUpWindowDark
            !isSystemTheme.value && !isDarkMode.value -> ColorButtonPopUpWindowLight
            else -> ColorButtonPopUpWindowLight
        }
    }
}