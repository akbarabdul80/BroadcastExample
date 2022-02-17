package com.zero.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class CustomReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        var msg = "This is default message"

        when (intent.action) {
            Intent.ACTION_POWER_CONNECTED -> msg = "The power is connected"
            Intent.ACTION_POWER_DISCONNECTED -> msg = "The power is disconnected"
            ACTION_CUSTOM_BROADCAST -> msg = intent.getStringExtra(DATA_SEND).toString()
        }

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

    }

    companion object {
        const val ACTION_CUSTOM_BROADCAST = "ACTION_CUSTOM_BROADCAST"
        const val DATA_SEND = "DATA_BROADCAST"
    }
}