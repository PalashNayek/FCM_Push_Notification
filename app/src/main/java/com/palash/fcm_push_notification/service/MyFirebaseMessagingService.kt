package com.palash.fcm_push_notification.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.palash.fcm_push_notification.MainActivity
import com.palash.fcm_push_notification.NotificationActivity
import com.palash.fcm_push_notification.R
import com.palash.fcm_push_notification.fragments.MainFragment
import com.palash.fcm_push_notification.fragments.SecondFragment
import com.palash.fcm_push_notification.utils.Constants.channelId
import com.palash.fcm_push_notification.utils.Constants.channelName

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification!=null){
            generateNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)
            //working - open app
            Log.d("CheckingTakon", "hahahahahahah")
        }else{
            Log.d("CheckingTakon", "kekekekkekeek")
        }
        Log.d("CheckingTakon", "tutuututtuuttuut")
    }

    private fun generateNotification(title: String, message: String) {

        val intent = Intent(this, NotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra("fragment", "specificFragment")
            //addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }


        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        //channel Id, Channel Name
        var builder: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, channelId)
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setAutoCancel(true)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)
        builder = builder.setContent(getRemoteView(title, message))

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notificationChannel)

        notificationManager.notify(0, builder.build())
    }

    private fun getRemoteView(title: String, message: String): RemoteViews {
        val remoteView = RemoteViews("com.palash.fcm_push_notification", R.layout.notification)
        remoteView.setTextViewText(R.id.title, title)
        remoteView.setTextViewText(R.id.message, message)
        remoteView.setImageViewResource(R.id.appLogo, R.drawable.ic_stat_ic_notification)

        return remoteView
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}