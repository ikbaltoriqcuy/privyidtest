package com.example.privytest.service

import Notification.showNotification
import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NotificationReciever : FirebaseMessagingService() {

    val i: Intent = Intent()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        if (message.notification != null) {
            GlobalScope.launch {
                withContext(Dispatchers.Main) {

                    showNotification(
                        this@NotificationReciever,
                        message.notification?.title.toString(),
                        message.notification?.body.toString()
                    )
                }
            }
        }
    }
}