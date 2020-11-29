package com.tcousin.leaguedataremastered.http.historic

import com.tcousin.leaguedataremastered.model.matchs.MatchsList
import com.tcousin.leaguedataremastered.model.matchs.games.Game
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface HistoricApiService {

    @GET(HistoricApiRouting.MATCH)
    fun getMatches(
        @Header("X-Riot-Token") token: String,
        @Path("accountId") accountId: String
    ): Call<MatchsList>

    @GET()
    fun getMatch()

    @GET(HistoricApiRouting.GAMEINFO)
    fun getGameInfo(
        @Header("X-Riot-Token") token: String,
        @Path("matchId") matchId: Long
    ): Call<Game>

}