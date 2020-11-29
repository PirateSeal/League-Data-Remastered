package com.tcousin.leaguedataremastered.database

import android.content.Context
import android.content.SharedPreferences

class DataStorage(context: Context, prefContext: String = "DefaultAppPreferences") {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(prefContext, Context.MODE_PRIVATE)

    fun putString(key: String, value: String) {
        return prefs.edit()
            .putString(key, value)
            .apply()
    }

    fun getString(key: String): String {
        val value: String? = prefs.getString(key, "")
        return value ?: ""
    }
}