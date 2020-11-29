package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
                TODO("Not yet implemented")
            }
        })

        setContentView(R.layout.activity_main)

            Toast.makeText(applicationContext, getString(R.string.app_name), Toast.LENGTH_LONG)
                .show()
            intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
    }
}