package com.ardinata.test.wlb.core.util

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import com.ardinata.test.wlb.config.constant.Cache
import com.ardinata.test.wlb.config.constant.Config
import com.yariksoffice.lingver.Lingver
import java.util.*


object LocaleUtil {

    private val TAG = this::class.java.simpleName
    /**
     *
     * The language in BCP-47 format
     * (ref: https://github.com/libyal/libfwnt/wiki/Language-Code-identifiers).
     *
     */
    const val ENGLISH_LANG = "en"
    const val INDONESIAN_LANG = "in"

    /*************************************
     *
     * Private
     *
     ************************************/

    private fun getPreferredLanguage(context: Context): String {
        val selectedLanguage = getLanguageSelection(context)
        return if (selectedLanguage.isNotEmpty()) {
            selectedLanguage
        } else {
            getSystemLanguage()
        }
    }


    /*************************************
     *
     * Public
     *
     ************************************/

    private fun setLanguageSelection(context: Context, toLang: String) {
        CacheUtil.set(
            context,
            Cache.LANGUAGE_SELECTION,
            toLang,
            Config.SHARED_PREFERENCE_NAME_UNCLEARABLE
        )
    }

    fun getLanguageSelection(context: Context): String {
        return CacheUtil.get(
            context,
            Cache.LANGUAGE_SELECTION,
            "",
            Config.SHARED_PREFERENCE_NAME_UNCLEARABLE
        )
    }

    fun getSystemLanguage(): String {
        val sysConfig: Configuration = Resources.getSystem().configuration
        return if (getLocaleCompat(sysConfig).language == INDONESIAN_LANG)
            INDONESIAN_LANG
        else
            ENGLISH_LANG
    }

    @Suppress("DEPRECATION")
    fun getLocaleCompat(configuration: Configuration): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // access the selected (top) language in device setting
            configuration.locales.get(0)
        } else {
            configuration.locale
        }
    }

    fun init(application: Application) {
        val preferredLanguage = getPreferredLanguage(application.applicationContext)
        Lingver.init(application, preferredLanguage)
        setLanguageSelection(application.applicationContext, preferredLanguage)
    }

    fun onConfigurationChanged(context: Context) {
        changeLanguage(context, getSystemLanguage())
    }

    fun changeLanguage(context: Context, language: String) {
        Lingver.getInstance().setLocale(context, language)
        setLanguageSelection(context, language)
    }



    @SuppressLint("ObsoleteSdkInt")
    fun updateConfigurationIfSupported(config: Configuration?, customLocale: Locale?): Configuration? {
        // Configuration.getLocales is added after 24 and Configuration.locale is deprecated in 24
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (config?.locales?.isEmpty != true) {
                return config
            }
        } else {
            if (config?.locale != null) {
                return config
            }
        }

        if (customLocale != null) {
            // Configuration.setLocale is added after 17 and Configuration.locale is deprecated
            // after 24
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config?.setLocale(customLocale)
            } else {
                config?.locale = customLocale
            }
        }
        return config
    }
}

