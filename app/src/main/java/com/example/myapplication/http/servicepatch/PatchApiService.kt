package com.example.myapplication.http.servicepatch

import com.example.myapplication.model.champions.Champions
import retrofit2.Call
import retrofit2.http.GET

interface PatchApiService {

    @GET(ApiRouting.PATCH)
    fun getPatchVersion(): Call<ArrayList<String>>

    @GET(ApiRouting.CHAMPION)
    fun getChampions(): Call<Champions>
}