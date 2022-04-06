package com.example.testtaskpingocean.data

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    companion object {
        private const val TOKEN = "token"
        private const val NAME = "name"
    }

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    var token: String?
        get() = sharedPreferences.getString(TOKEN, "")
        set(value) = sharedPreferences.edit().putString(TOKEN, value).apply()
}