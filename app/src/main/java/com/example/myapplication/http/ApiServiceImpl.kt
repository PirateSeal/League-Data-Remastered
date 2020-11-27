package com.example.myapplication.http

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

object ApiServiceImpl {
    private val apiService = RetrofitClient.getClient().create(ApiService::class.java)

    private lateinit var errorHandler: ErrorHandler
    private lateinit var fillHandler: InfoFiller

    interface ErrorHandler {
        fun errorSummoner()
        fun errorRank()
    }

    fun setListener(listener: ErrorHandler) {
        errorHandler = listener
    }

    interface InfoFiller {
        fun fillSummonerIcon(profileIconId: Int)
        fun fillSummonerLvl(summonerLevel: Int)
    }


    fun setFiller(listener: InfoFiller) {
        fillHandler = listener
    }


    fun getSummoner(summonerName: String) {
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = apiService.getSummoner(summonerName).awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    withContext(Dispatchers.Main) {
                        fillHandler.fillSummonerIcon(data.profileIconId)
                        fillHandler.fillSummonerLvl(data.summonerLevel)
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