package com.example.composablepdd.application.reminder

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.composablepdd.R
import com.example.composablepdd.application.MainActivity
import javax.inject.Inject

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        showNotification(context)
    }

    private fun showNotification(context: Context){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_MUTABLE else 0

        val activityIntent = Intent(
            context,
            MainActivity::class.java
        )
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            2,
            activityIntent,
            flag
        )
        val incrementIntent = PendingIntent.getBroadcast(
            context,
            3,
            Intent(context, ReferredReminderReceiver::class.java).apply {
                putExtra("extra", 30 )
            },
            flag
        )

        val notification = NotificationCompat.Builder(context, "channel_id")
            .setSmallIcon(R.drawable.ic_school)
            .setContentTitle("Не забудьте порешать билеты")
            .setContentText("")
            .setContentIntent(activityPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(
                R.drawable.ic_time,
                "Скрыть ",
                incrementIntent
            )
            .build()
        notificationManager.notify(1, notification )
    }
}