package com.example.myapplication.http.servicepatch

import com.example.myapplication.http.RetrofitClient
import com.example.myapplication.model.champions.Champions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

object ApiCdnServiceImpl {

    private val cdnApiService =
        RetrofitClient.getCdnClient()
            .create(PatchApiService::class.java)


    private lateinit var errorHandler: ErrorHandler
    private lateinit var fillHandler: InfoFiller

    interface ErrorHandler {
        fun errorPatch()
    }

    interface InfoFiller {
        fun fillPatch(patch: String)
    }

    fun setListener(listener: ErrorHandler) {
        errorHandler = listener
    }

    fun setFiller(listener: InfoFiller) {
        fillHandler = listener
    }


    fun getPatchVersion() {
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = cdnApiService.getPatchVersion().awaitResponse()
                if (response.isSuccessful) {
                    val patchVersion = response.body()!!.first()
                    withContext(Dispatchers.Main) {
                        fillHandler.fillPatch(patchVersion)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorHandler.errorPatch()
                }
            }
        }
    }

    suspend fun getChampion(championId: Int)  {
        try {
            val response = cdnApiService.getChampions().awaitResponse()
            if (response.isSuccessful) {
                val champions : Champions? = response.body()

                val champion = champions?.let { listOf(it.data) }

                println(champion)

            }
        } catch (e: Exception) {
                errorHandler.errorPatch()
        }
    }
}