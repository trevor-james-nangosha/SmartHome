package com.nangosha.smarthome.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.nangosha.smarthome.R

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(ctx: Context, intent: Intent?) {
        val content = intent?.getStringExtra("content")
        val title = intent?.getStringExtra("routine_name")
        val id = intent?.getIntExtra("id", 1)

        val builder = NotificationCompat.Builder(ctx, "SmartHomeChannelID")
            .setSmallIcon(R.drawable.smarthome_notification_icon)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(ctx)) {
            // notificationId is a unique int for each notification that you must define
            // todo, this could be a bug since every notification i am firing will have the same ID. should change it.
            if (id != null) {
//                Log.d("NotificationID", "$id")
                notify(id, builder.build())
            }
        }
    }

}