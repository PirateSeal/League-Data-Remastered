package com.example.myapplication.http

import com.example.myapplication.BuildConfig
import com.example.myapplication.model.ModelSummoner
import com.example.myapplication.model.ranked.ModelRank
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

object ApiServiceImpl {
    private val apiService = RetrofitClient.getApiClient().create(ApiService::class.java)
    private val patchApiService =
        RetrofitClient.getCdnClient()
            .create(PatchApiService::class.java)

    private lateinit var errorHandler: ErrorHandler
    private lateinit var fillHandler: InfoFiller

    interface ErrorHandler {
        fun errorSummoner()
        fun errorRank()
        fun errorPatch()
    }

    fun setListener(listener: ErrorHandler) {
        errorHandler = listener
    }

    interface InfoFiller {
        fun fillSummonerData(summoner: ModelSummoner)
        fun fillPatch(patch: String)
        fun fillRanked(modelRank: ModelRank)
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
                val response = apiService.getUserRank(summonerId).awaitResponse()
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

    fun getPatchVersion() {
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = patchApiService.getPatchVersion().awaitResponse()
                if (response.isSuccessful) {
                    val patchVersion = response.body()!!.first()
                    withContext(Dispatchers.Main) {
                        fillHandler.fillPatch(patchVersion)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorHandler.errorPatch()
                }
            }
        }
    }


}