package com.tcousin.leaguedataremastered.http.serviceapi

import com.tcousin.leaguedataremastered.BuildConfig
import com.tcousin.leaguedataremastered.http.RetrofitClient
import com.tcousin.leaguedataremastered.model.ModelSummoner
import com.tcousin.leaguedataremastered.model.ranked.ModelRank
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

object ApiServiceImpl {
    private val apiService = RetrofitClient.getApiClient().create(ApiService::class.java)

    private lateinit var errorHandler: ErrorHandler
    private lateinit var fillHandler: InfoFiller

    interface ErrorHandler {
        fun errorSummoner()
        fun errorRank()
    }

    interface InfoFiller {
        fun fillSummonerData(summoner: ModelSummoner)
        fun fillRanked(modelRank: ModelRank)
    }

    fun setListener(listener: ErrorHandler) {
        errorHandler = listener
    }

    fun setFiller(listener: InfoFiller) {
        fillHandler = listener
    }


    fun getSummoner(summonerName: String) {
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response =
                    apiService.getSummoner(BuildConfig.TOKEN, summonerName).awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    withContext(Dispatchers.Main) {
                        fillHandler.fillSummonerData(data)
                        println(data)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    println(e)
                    errorHandler.errorSummoner()

                }
            }
        }
    }


    fun getSummonerRanks(summonerId: String) {
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = apiService.getUserRank(BuildConfig.TOKEN, summonerId).awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!

                    withContext(Dispatchers.Main) {
                        fillHandler.fillRanked(data)
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