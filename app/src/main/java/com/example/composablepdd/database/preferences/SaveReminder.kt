package com.example.composablepdd.database.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.composablepdd.application.reminder.Alarm
import java.util.Calendar

class SaveReminder(
    private val applicationContext: Application,
    private val alarm: Alarm
) {
    private val currentTime = Calendar.getInstance()
    val prefs: SharedPreferences =
        applicationContext.getSharedPreferences("my_settings_prefs", Context.MODE_PRIVATE)

    val listReplayItemText =
        listOf("Нет", "Каждый день", "Раз в 2 дня", "Раз в неделю", "2 раза в месяц", "Раз в месяц")

    var initialHour = prefs.getInt("reminder_hour", 99)
    var initialMinute = prefs.getInt("reminder_minute", 99)

    fun onClickSaveReminder(
        replayItemCheck: Int,
    ){
        if (replayItemCheck == 0){
            prefs.edit().putInt("reminder_hour", 99).apply()
            prefs.edit().putInt("reminder_minute", 99).apply()
            prefs.edit().putInt("reminder_replay", replayItemCheck).apply()
        } else {
            prefs.edit().putInt("reminder_hour", initialHour).apply()
            prefs.edit().putInt("reminder_minute", initialMinute).apply()
            prefs.edit().putInt("reminder_replay", replayItemCheck).apply()
        }
        setAlarm(replayItemCheck)
    }

    fun onClickReplayItem(
        index: Int
    ){
        if (index == 0){
            initialHour = currentTime.get(Calendar.HOUR_OF_DAY)
            initialMinute = currentTime.get(Calendar.MINUTE)
        }
    }
    private fun setAlarm(
        replayItemCheck: Int
    ){
        alarm.setAlarm(
            hour = initialHour,
            minute = initialMinute,
            reverse = replayItemCheck
        )
    }
}