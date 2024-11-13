package com.example.composablepdd.application.reminder

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import java.util.Calendar

class Alarm(private val applicationContext: Application) {

    fun setAlarm(hour: Int, minute: Int, reverse: Int) {
        val alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(applicationContext, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }


        val intervalMillis = when (reverse) {
            1 -> AlarmManager.INTERVAL_DAY
            2 -> AlarmManager.INTERVAL_DAY * 2
            3 -> AlarmManager.INTERVAL_DAY * 7
            4 -> AlarmManager.INTERVAL_DAY * 15
            5 -> AlarmManager.INTERVAL_DAY * 30
            else -> 0
        }

        if (reverse == 0) {
            cancelAlarm()
        } else {

            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                intervalMillis,
                pendingIntent
            )
        }

        Log.d("MyLog", "Alarm set for: $hour hours, $minute minutes, repeating every: ${intervalMillis / 1000 / 60 / 60 / 24} day")
    }

    private fun cancelAlarm() {
        val alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(applicationContext, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)

        alarmManager.cancel(pendingIntent)
    }
}