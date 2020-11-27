package com.example.myapplication.http

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

object ApiServiceImpl {
    private val apiService = RetrofitClient.getClient().create(ApiService::class.java)

    private lateinit var errorHandler : ErrorHandler

    interface ErrorHandler {
        fun errorSummoner();
        fun errorRank()
    }

    fun setListener(listener : ErrorHandler) {
        errorHandler = listener
    }

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
                    errorHandler.errorSummoner()
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
                    errorHandler.errorRank()
                }
            }
        }
    }


}