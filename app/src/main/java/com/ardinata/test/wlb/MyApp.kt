package com.ardinata.test.wlb

import android.app.Application
import com.ardinata.test.wlb.core.util.LocaleUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        LocaleUtil.init(this)
    }
}