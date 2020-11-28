package com.example.myapplication.http.servicepatch

import retrofit2.Call
import retrofit2.http.GET

interface PatchApiService {

    @GET(ApiRouting.PATCH)
    fun getPatchVersion(): Call<ArrayList<String>>
}