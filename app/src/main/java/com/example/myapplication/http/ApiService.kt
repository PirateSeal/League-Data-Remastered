package com.example.myapplication.http

import com.example.myapplication.model.ModelSummoner
import com.example.myapplication.model.ranked.ModelRank
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/summoner/v4/summoners/by-name/{summonerName}")
    fun getSummoner(summonerName:String): Call<ModelSummoner>

    @GET("/league/v4/entries/by-summoner/{id}")
    fun getUserRank(id:String): Call<ModelRank>

    
}