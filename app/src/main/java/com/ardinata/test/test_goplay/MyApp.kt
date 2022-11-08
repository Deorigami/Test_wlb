package com.ardinata.test.test_goplay

import android.app.Application
import com.ardinata.test.test_goplay.core.util.LocaleUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        LocaleUtil.init(this)
    }
}