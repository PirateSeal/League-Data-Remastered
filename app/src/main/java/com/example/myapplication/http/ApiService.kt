package com.example.myapplication.http

import retrofit2.http.GET

interface ApiService {

    @GET("")
    fun getUser()
}