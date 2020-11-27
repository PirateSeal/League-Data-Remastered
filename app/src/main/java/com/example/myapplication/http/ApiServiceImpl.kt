package com.example.myapplication.http

import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

object ApiServiceImpl {
    private val apiService = RetrofitClient.getClient().create(ApiService::class.java)

    fun getSummoner(summonerName: String) {
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = apiService.getSummoner(summonerName).awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.d("SUMMONER", data.toString())

                    withContext(Dispatchers.Main) {
                        //TODO FILL SUMMONER RELATED TEXT
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        applicationContext,
                        "Could not find this summoner",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    fun getSummonerRanks(summonerId: String) {
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = apiService.getUserRank(summonerId).awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.d("RANKS", data.toString())

                    withContext(Dispatchers.Main) {
                        //TODO FILL RANKED INFOS FROM SUMMONER ID
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        applicationContext,
                        "Could not find user rank",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


}