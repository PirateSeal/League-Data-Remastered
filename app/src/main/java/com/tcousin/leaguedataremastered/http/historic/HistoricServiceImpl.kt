package com.tcousin.leaguedataremastered.http.historic

import com.tcousin.leaguedataremastered.BuildConfig
import com.tcousin.leaguedataremastered.http.RetrofitClient
import com.tcousin.leaguedataremastered.model.matchs.MatchsList
import com.tcousin.leaguedataremastered.model.matchs.games.Game
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
        fun fillMatchInfo(game: Game)
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

    suspend fun getGameInfo(gameId: Long) {
        try {
            val response =
                historicApiService.getGameInfo(BuildConfig.TOKEN, gameId).awaitResponse()
            if (response.isSuccessful) {
                val game = response.body()!!
                fillHandler.fillMatchInfo(game)
            }
            println("33")
        } catch (e: Exception) {
            errorHandler.errorMatches()
        }

    }
}
