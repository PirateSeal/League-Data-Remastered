package com.example.myapplication.http.historic

import com.example.myapplication.BuildConfig
import com.example.myapplication.http.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.lang.Exception


object HistoricServiceImpl {

    private val historicApiService = RetrofitClient.getApiClient().create(HistoricApiService::class.java)

    private lateinit var errorHandler: ErrorHandler
    private lateinit var fillHandler: InfoFiller


    interface ErrorHandler {
        fun errorMatches()
    }

    interface InfoFiller {
    fun fillMatchList()
    }

    fun setListener(listener: ErrorHandler) {
      errorHandler = listener
    }

    fun setFiller(listener: InfoFiller) {
      fillHandler = listener
    }

    fun getMatches(accountId: String){
        GlobalScope.launch(Dispatchers.IO){
            try{
                var response = historicApiService.getMatches(BuildConfig.TOKEN ,accountId).awaitResponse()
                if (response.isSuccessful){
                    val data = response.body()!!

                }

            }
            catch (e: Exception){
                withContext(Dispatchers.Main){
                    errorHandler.errorMatches()
                }
            }
        }
    }


}