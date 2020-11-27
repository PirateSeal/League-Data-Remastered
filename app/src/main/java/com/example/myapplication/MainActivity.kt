package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.http.ApiServiceImpl

const val BASE_URL = "https://euw1.api.riotgames.com/lol"
const val ASSETS_URL = "https://euw1.api.riotgames.com/lol"
const val PATCH_VERSION_URL = "https://ddragon.leagueoflegends.com/api/versions.json"

class MainActivity : AppCompatActivity() {
    var TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button  = findViewById<Button>(R.id.main_button);
        button.setOnClickListener{
            Toast.makeText(applicationContext, getString(R.string.app_name), Toast.LENGTH_LONG).show()
             intent =  Intent(this, SecondActivity::class.java)
            startActivity(intent)
            //finish() clean the current activity so going back will exit the app
        }

        val api = ApiServiceImpl

        api.getSummoner("Le Phoque Pirate");

    }
}