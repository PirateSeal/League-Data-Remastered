package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.http.ApiServiceImpl


const val PATCH_VERSION_URL = "https://ddragon.leagueoflegends.com/api/versions.json"
const val VERSION = "9.3.1"
const val ASSETS_URL = "https://ddragon.leagueoflegends.com/cdn/$VERSION/img/"
val api = ApiServiceImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val button  = findViewById<Button>(R.id.main_button);
        button.setOnClickListener{

            api.getPatchVersion();
            Toast.makeText(applicationContext, getString(R.string.app_name), Toast.LENGTH_LONG).show()
             intent =  Intent(this, SecondActivity::class.java)
            startActivity(intent)
            //finish() clean the current activity so going back will exit the app
        }
    }
}