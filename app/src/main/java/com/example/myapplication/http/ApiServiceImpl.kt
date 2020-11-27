package com.example.myapplication.http

import com.example.myapplication.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

object ApiServiceImpl {
    private val apiService = RetrofitClient.getClient().create(ApiService::class.java)
    private val patchApiService = RetrofitClient.getCustomClient("https://ddragon.leagueoflegends.com/").create(PatchApiService::class.java)

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
                val response = apiService.getSummoner(BuildConfig.TOKEN, summonerName).awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    withContext(Dispatchers.Main) {
                        fillHandler.fillSummonerIcon(data.profileIconId)
                        fillHandler.fillSummonerLvl(data.summonerLevel)
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
    fun getPatchVersion(){
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = patchApiService.getPatchVersion().awaitResponse()
                if(response.isSuccessful){

                    val patchVersion = response.body()!!.first()
                    println(patchVersion);

                withContext(Dispatchers.Main){
                    //TODO
                }
                }
                }
            catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorHandler.errorRank()
                }
            }
        }
    }


}