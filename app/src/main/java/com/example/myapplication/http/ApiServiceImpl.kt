package com.example.myapplication.http

object ApiServiceImpl{
    private val apiService = RetrofitClient.getClient().create(ApiService::class.java)



}