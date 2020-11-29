package com.example.myapplication.http.historic

import com.example.myapplication.BuildConfig
import com.example.myapplication.http.RetrofitClient
import com.example.myapplication.model.matchs.MatchsList
import com.example.myapplication.model.matchs.games.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse


object HistoricServiceImpl {

    private val historicApiService =
        RetrofitClient.getApiClient().create(HistoricApiService::class.java)

    private lateinit var errorHandler: ErrorHandler
    private lateinit var fillHandler: InfoFiller


    interface ErrorHandler {
        fun errorMatches()
    }

    interface InfoFiller {
        suspend fun fillMatchList(matchList: MatchsList)
    }

    fun setListener(listener: ErrorHandler) {
        errorHandler = listener
    }

    fun setFiller(listener: InfoFiller) {
        fillHandler = listener
    }

    fun getMatches(accountId: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response =
                    historicApiService.getMatches(BuildConfig.TOKEN, accountId).awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    fillHandler.fillMatchList(data)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorHandler.errorMatches()
                }
            }
        }
    }

    suspend fun getGameInfo(gameId: Long): Game? {
        try {
            val response =
                historicApiService.getGameInfo(BuildConfig.TOKEN, gameId).awaitResponse()
            if (response.isSuccessful) {
                return response.body()!!
            }

        } catch (e: Exception) {
            errorHandler.errorMatches()
        }

        return null
    }


}