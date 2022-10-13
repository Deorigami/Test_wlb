package com.ardinata.test.wlb.core.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.ardinata.test.wlb.config.constant.Config
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody

object CacheUtil {

    private val TAG = this::class.java.simpleName

    fun <T> set(context: Context, key: String, value: T,  configName: String = Config.SHARED_PREFERENCE_NAME) {
        val sharedPref = getPreference(context, configName)

        with(sharedPref.edit()) {
            when (value) {
                is Boolean -> putBoolean(key, value as Boolean)
                is String -> putString(key, value as String)
                is Int -> putInt(key,value as Int)
                is Long -> putLong(key,value as Long)
            }

            commit()
        }
    }

    fun <T> get(context: Context, key: String, default: T, configName: String = Config.SHARED_PREFERENCE_NAME): T {
        val sharedPref = getPreference(context, configName)

        return when (default) {
            is Boolean -> sharedPref.getBoolean(key, default) as T
            is String -> sharedPref.getString(key, default) as T
            is Int -> sharedPref.getInt(key,default) as T
            is Long -> sharedPref.getLong(key,default) as T

            else -> default
        }
    }

    private fun getPreference(context: Context, configName: String = Config.SHARED_PREFERENCE_NAME): SharedPreferences {
        return context.getSharedPreferences(configName, Context.MODE_PRIVATE)
    }

    fun clear(context: Context): Boolean {
        val sharedPref = getPreference(context)
        return sharedPref.edit().clear().commit()
    }

    fun clear(context: Context, configName: String = Config.SHARED_PREFERENCE_NAME) {
        val sharedPref = getPreference(context, configName)
        sharedPref.edit().clear().apply()
    }

    fun remove(context: Context, configName: String = Config.SHARED_PREFERENCE_NAME, key: String) {
        val sharedPref = getPreference(context, configName)
        sharedPref.edit().remove(key).apply()
    }

    fun clearCacheByKey(context: Context, configName: String = Config.SHARED_PREFERENCE_NAME_NETWORK, url: String, vararg requests: String = arrayOf()) {
        val sharedPref = getPreference(context, configName)
        for (mutableEntry in sharedPref.all) {
            if(mutableEntry.key.contains(url)) {
                val keys = mutableEntry.key.split(";")
                val keyUrl = keys[0]
                val keyRequest = keys[1]
                if (keyUrl == url) {
                    requests.forEach {
                        if (!keyRequest.contains(it))
                        {
                            Log.d(TAG, "key to be deleted not found")
                            return
                        }
                    }
                    Log.d(TAG, "key to be deleted: ${mutableEntry.key}")
                    remove(context, configName, mutableEntry.key)
                }
            }
        }
        return
    }

    fun getCache(context: Context, request: Request, configName: String = Config.SHARED_PREFERENCE_NAME_NETWORK): ResponseBody {
        val key = "${request.url};${InterceptorUtil.bodyToString(request.body)}"
        val json = get(
            context,
            key,
            "",
            configName
        )
        return json.toResponseBody("application/json".toMediaTypeOrNull())
    }

    fun saveCache(context: Context, request: Request, response: String?, configName: String = Config.SHARED_PREFERENCE_NAME_NETWORK) {
        val key = "${request.url};${InterceptorUtil.bodyToStringWithoutLang(request.body)}"
        set(
            context,
            key,
            response,
            configName
        )
    }

    fun checkCache(context: Context, request: Request, configName:String = Config.SHARED_PREFERENCE_NAME_NETWORK): Boolean {
        val key = "${request.url};${InterceptorUtil.bodyToString(request.body)}"
        return get(
            context,
            key,
            "",
            configName
        ).isNotEmpty()
    }

    fun getCacheByKey(context: Context, configName: String = Config.SHARED_PREFERENCE_NAME_NETWORK, url: String, vararg requests: String = arrayOf()): String {
        val sharedPref = getPreference(context, configName)
        for (mutableEntry in sharedPref.all) {
            if(mutableEntry.key.contains(url)) {
                val keys = mutableEntry.key.split(";")
                val keyUrl = keys[0]
                val keyRequest = keys[1]
                if (keyUrl == url) {
                    requests.forEach {
                        if (!keyRequest.contains(it))
                            return ""
                    }
                    return mutableEntry.value as String
                }
            }
        }
        return ""
    }

    fun removeCacheVersionByKey(context: Context, configName: String = Config.SHARED_PREFERENCE_NAME_NETWORK, url: String){
        val sharedPref = getPreference(context, configName)
        for (mutableEntry in sharedPref.all) {
            if(mutableEntry.key.contains(url)) {
                remove(context, configName, mutableEntry.key)
            }
        }
    }

    fun getCacheByKeyRequest(context: Context, configName: String = Config.SHARED_PREFERENCE_NAME_NETWORK, url: String, vararg requests: String = arrayOf()): String {
        val sharedPref = getPreference(context, configName)
        requests.forEach {
            for (mutableEntry in sharedPref.all) {
                if(mutableEntry.key.contains(it) && mutableEntry.key.contains(url)) {
                    return mutableEntry.value as String
                }
            }
        }
        return ""
    }

    fun getUnclearableConfigName():String{
        return Config.SHARED_PREFERENCE_NAME_UNCLEARABLE
    }
}