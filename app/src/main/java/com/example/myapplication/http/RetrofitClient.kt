package com.example.myapplication.http

import com.example.myapplication.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {

    private var retrofit: Retrofit? = null

    fun getOkHttpClient():  OkHttpClient{
        var httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .cache(null)
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor {
                chain ->
                chain.proceed(chain.request()
                    .newBuilder()
                    .build()
                )
            }
            .callTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2,TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .build()
    }

    fun getClient(): Retrofit{

        if(retrofit == null ) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build()

        }
        return retrofit!!
    }
}