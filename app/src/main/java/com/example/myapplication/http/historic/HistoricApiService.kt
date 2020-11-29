package com.example.myapplication.http.historic

import com.example.myapplication.model.matchs.MatchsList
import com.example.myapplication.model.matchs.games.Game
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface HistoricApiService {

    @GET(HistoricApiRouting.MATCH)
    fun getMatches( @Header("X-Riot-Token") token: String,
                    @Path("accountId") accountId: String): Call<MatchsList>

    @GET()
    fun getMatch()

    @GET(HistoricApiRouting.GAMEINFO)
    fun getGameInfo(): Call<Game>

}