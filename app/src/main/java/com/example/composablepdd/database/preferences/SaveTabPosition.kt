package com.example.composablepdd.database.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableIntStateOf

class SaveTabPosition (
    private val applicationContext: Application
){
    private val prefs: SharedPreferences = applicationContext.getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)

    var currentPage = mutableIntStateOf(prefs.getInt("current_page", 0))

    fun saveCurrentPage(page: Int) {
        currentPage.intValue = page
        prefs.edit().putInt("current_page", page).apply()
    }
}