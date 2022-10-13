package com.ardinata.test.wlb.core.extension

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> tryDeserialize(json : String) : T?{
    val type = object : TypeToken<T>() {}.type
    return try {
        Gson().fromJson(json , type)
    } catch (e : Exception) {
        Log.d("DESERIALIZE_ERROR", "${e.message}")
        null
    }
}