package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_second.bottom_bar as bottom_bar



class SecondActivity : AppCompatActivity() {

    lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tv = findViewById(R.id.second_title)

        bottom_bar.onTabSelected = { tab ->
            when (tab.title){
                "Home" -> showFragment1()
                "Alarm" -> showFragment2()
                "Bread" -> showFragment3()
                "Cart" -> showFragment4()
                else -> showFragment1()
            }

        }
    }

    fun sendNotification (){

        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "YOUR_CHANNEL_ID",
                "YOUR_CHANNEL_NAME",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "YOUR_NOTIFICATION_CHANNEL_DESCRIPTION"
            mNotificationManager.createNotificationChannel(channel)
        }


      val builder =  NotificationCompat.Builder(this, "YOUR_CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(1, builder.build())
        }
    }

    private fun showFragment1(){
        supportFragmentManager.beginTransaction().replace(R.id.containerFragment, Fragment1()).commit()
    }

    private fun showFragment2(){
        supportFragmentManager.beginTransaction().replace(R.id.containerFragment, Fragment2()).commit()

    }

    private fun showFragment3(){
        supportFragmentManager.beginTransaction().replace(R.id.containerFragment, Fragment3()).commit()

    }
    private fun showFragment4(){
        supportFragmentManager.beginTransaction().replace(R.id.containerFragment, ProfileFragment()).commit()
    }


}