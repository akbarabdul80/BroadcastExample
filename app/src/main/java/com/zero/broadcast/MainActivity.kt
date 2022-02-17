package com.zero.broadcast

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.zero.broadcast.CustomReceiver.Companion.ACTION_CUSTOM_BROADCAST
import com.zero.broadcast.CustomReceiver.Companion.DATA_SEND
import com.zero.broadcast.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mCustomReceiver = CustomReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)

        registerReceiver(mCustomReceiver, intentFilter)

        binding.btnSend.setOnClickListener {
            val intent =
                Intent(ACTION_CUSTOM_BROADCAST).putExtra(DATA_SEND, "This is custom broadcast")
            LocalBroadcastManager.getInstance(baseContext).sendBroadcast(intent)
        }

        val intentFilter1 = IntentFilter(ACTION_CUSTOM_BROADCAST)
        LocalBroadcastManager.getInstance(this).registerReceiver(mCustomReceiver, intentFilter1)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mCustomReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mCustomReceiver)
    }


}