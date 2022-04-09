package com.example.homework

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat

class MyBroadCast: BroadcastReceiver()
{
    override fun onReceive(p0: Context?, p1: Intent?)
    {
        if (p1 != null)
        {
            when(p1.action)
            {
                Intent.ACTION_BATTERY_LOW -> displayStatus(p0,
                    "Your Battery is Below 10% Put The Charger",
                    "Battery Low", R.drawable.ic_battery_low)

                Intent.ACTION_POWER_CONNECTED -> displayStatus(p0,
                    "Charger Connected Keep it until it`s full",
                    "Charger Connected", R.drawable.ic_battery_charging)

                Intent.ACTION_POWER_DISCONNECTED -> displayStatus(p0,
                    "Charger Disconnected",
                    "Charger Disconnected", R.drawable.ic_battery)
            }
        }
    }


    private fun displayStatus(context: Context?,
                                   message: String, title: String,
                                    icon: Int)
    {
        val dialog = AlertDialog.Builder(context)
            .setMessage(message)
            .setTitle(title)
            .setIcon(icon)
            .create()
            .show()
        startNotification(context, message, title, icon)
    }

    private fun startNotification(context: Context?,
                                  message: String,
                                  title: String,
                                  icon: Int)
    {
        val channelID = "EXAMPLE"
        val manager: NotificationManager?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val serviceChannel = NotificationChannel(channelID,
                "Example Service Channel", NotificationManager.IMPORTANCE_DEFAULT)

            manager = context!!.getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
        else
            manager = context!!.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager


        val notificationIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context,
            0, notificationIntent, 0)

        val notification = NotificationCompat.Builder(context, channelID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(icon)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources ,
                icon))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        manager!!.notify(1, notification)
    }

}