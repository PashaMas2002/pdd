package com.example.composablepdd.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.composablepdd.database.MainDataBase
import com.example.composablepdd.database.seeder.SeederDatabase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {
    val database by lazy { MainDataBase.getDataBase(this) }

    @Inject
    lateinit var seederDatabase: SeederDatabase

    override fun onCreate() {
        super.onCreate()
        seedDatabase()
        createNotificationChannel()
    }

    private fun seedDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            seederDatabase.seedDatabase()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "Напоминания",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Настройте напоминания в приложении , в разделе настройки"
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
