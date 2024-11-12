package com.example.composablepdd.database.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import java.util.Calendar

class SaveValueSettings(
    private val applicationContext: Application
) {
    private val prefs: SharedPreferences =
        applicationContext.getSharedPreferences("my_settings_prefs", Context.MODE_PRIVATE)


    var systemTheme = mutableStateOf(prefs.getBoolean("system_theme", true))

    fun saveSystemTheme(isCheck: Boolean) {
        systemTheme.value = isCheck
        prefs.edit().putBoolean("system_theme", isCheck).apply()
    }


    var darkMode = mutableStateOf(prefs.getBoolean("dark_mode", false))

    fun saveDarkMode(isCheck: Boolean) {
        darkMode.value = isCheck
        prefs.edit().putBoolean("dark_mode", isCheck).apply()
    }
}