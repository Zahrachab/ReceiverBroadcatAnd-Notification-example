package com.example.myapplicationannonce

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.content.ContextCompat.getSystemService
import android.location.LocationManager





class MyReceiver : BroadcastReceiver() {
    private val CUSTOM_BROADCAST_ACTION = "MARIAGE_BROADCAST";
    val CHANNEL_ID = "Noti12141"
    var mContext: Context ?= null
    var bitmap: Bitmap?= null

    override fun onReceive(context: Context, intent: Intent) {
        this.mContext = context

        val action = intent.action
        if (CUSTOM_BROADCAST_ACTION.equals(action)) {
            // When receive it will show an toast popup message.
            val name = intent.extras.getString("nom")
            val bibliographie = intent.extras.getString("bibliographie")
            val bitmap = intent.getParcelableExtra("photo") as Bitmap
            createNotificationChannel()

            // Step 3
            // To open the app when the user clicks on the notification.
            val intent = Intent(context, ProfilActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("nom", name)
            intent.putExtra("bibliographie", bibliographie)
            intent.putExtra("photo", bitmap)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

            // Set the notification content
            val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setLargeIcon(bitmap)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle(name)
                .setContentText(bibliographie)
                .addAction(R.drawable.notification_icon_background, "Interessé", pendingIntent)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText( bibliographie)
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)

            // Step 4: Show the notification
            val notificationManager = NotificationManagerCompat.from(context)
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(12141, mBuilder.build())
        }
    }
        // Step 2: Create a channel and set the importance
        private fun createNotificationChannel() {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "mariage "
                val description = ""
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance)
                channel.description = description
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                val notificationManager = mContext!!.getSystemService(NotificationManager::class.java) as NotificationManager
                notificationManager!!.createNotificationChannel(channel)
            }
        }


}
