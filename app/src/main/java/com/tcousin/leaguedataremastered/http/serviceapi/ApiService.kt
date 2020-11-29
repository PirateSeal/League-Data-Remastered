package com.tcousin.leaguedataremastered.http.serviceapi

import com.tcousin.leaguedataremastered.model.ModelSummoner
import com.tcousin.leaguedataremastered.model.ranked.ModelRank
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    @GET(ApiRouting.SUMMONER)
    fun getSummoner(
        @Header("X-Riot-Token") token: String,
        @Path("summonerName") summonerName: String
    ): Call<ModelSummoner>

    @GET(ApiRouting.RANKING)
    fun getUserRank(
        @Header("X-Riot-Token") token: String,
        @Path("id") id: String
    ): Call<ModelRank>
}