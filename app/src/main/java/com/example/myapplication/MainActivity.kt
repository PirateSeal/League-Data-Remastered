package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myapplication.database.DataStorage
import com.example.myapplication.http.servicepatch.ApiCdnServiceImpl

class MainActivity : AppCompatActivity() {
    private val patchApi = ApiCdnServiceImpl

    private lateinit var dataStorage: DataStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataStorage = DataStorage(this)

        patchApi.getPatchVersion()

        patchApi.setFiller(object : ApiCdnServiceImpl.InfoFiller {
            override fun fillPatch(patch: String) {
                dataStorage.putString("patch", patch)
            }
        })

        patchApi.setListener(object : ApiCdnServiceImpl.ErrorHandler {
            override fun errorPatch() {
                makeText(
                    applicationContext,
                    R.string.error_patch,
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        setContentView(R.layout.activity_main)

        Toast.makeText(applicationContext, getString(R.string.app_name), Toast.LENGTH_LONG)
            .show()
        intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        sendNotification();
    }

    private fun sendNotification() {
        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "LDR_CHANNEL_ID",
                "LDR_CHANNEL",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "CHANNEL_FOR_LDR"
            mNotificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, "LDR_CHANNEL_ID")
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.welcome))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
    }
}