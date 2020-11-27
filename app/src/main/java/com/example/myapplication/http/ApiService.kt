package com.example.myapplication.http

import com.example.myapplication.model.ModelSummoner
import com.example.myapplication.model.ranked.ModelRank
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    @GET(ApiRouting.SUMMONER)
    fun getSummoner(@Header("X-Riot-Token") token: String, @Path("summonerName") summonerName:String): Call<ModelSummoner>

    @GET("league/v4/entries/by-summoner/{id}")
    fun getUserRank(  @Path("id") id:String): Call<ModelRank>
}