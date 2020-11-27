package com.example.myapplication.http

import com.example.myapplication.model.ModelSummoner
import com.example.myapplication.model.ranked.ModelRank
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("summoner/v4/summoners/by-name/{summonerName}?api_key=RGAPI-ce5534f0-9df5-4d9f-8827-8fa18317f6ef")
    fun getSummoner(@Path("summonerName") summonerName:String): Call<ModelSummoner>

    @GET("league/v4/entries/by-summoner/{id}?api_key=RGAPI-ce5534f0-9df5-4d9f-8827-8fa18317f6ef")
    fun getUserRank(@Path("id") id:String): Call<ModelRank>

    
}