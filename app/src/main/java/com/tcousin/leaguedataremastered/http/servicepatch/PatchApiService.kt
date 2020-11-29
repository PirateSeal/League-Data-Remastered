package com.tcousin.leaguedataremastered.http.servicepatch

import com.tcousin.leaguedataremastered.model.champions.Champions
import retrofit2.Call
import retrofit2.http.GET

interface PatchApiService {

    @GET(ApiRouting.PATCH)
    fun getPatchVersion(): Call<ArrayList<String>>

    @GET(ApiRouting.CHAMPION)
    fun getChampions(): Call<Champions>
}