package com.ardinata.test.wlb.core.base

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.ardinata.core.R
import com.ardinata.test.wlb.core.extension.hideKeyboard
import com.ardinata.test.wlb.core.util.LocaleUtil
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
abstract class BaseActivity(val layout: Int) : AppCompatActivity(layout) {

// --------------------------------------------------
// --------------------------------------------------
// PROPERTIES
// --------------------------------------------------

    private var touchEvent : Int? = null

    // --------------------------------------
    // Locale setting
    // --------------------------------------

    private var locale: Locale? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)

        locale = Locale(LocaleUtil.getLanguageSelection(this))
        applyOverrideConfiguration(Configuration())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.statusBarColor = Color.BLACK
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d(
            "CONFIGURATION_CHANGED",
            "System default language:  ${LocaleUtil.getLocaleCompat(newConfig).language}"
        )
        LocaleUtil.onConfigurationChanged(this)
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        super.applyOverrideConfiguration(
            LocaleUtil.updateConfigurationIfSupported(
                overrideConfiguration,
                locale
            )
        )
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null && ev?.action == MotionEvent.ACTION_UP) {
            if (touchEvent == null){
                hideKeyboard()
                currentFocus?.clearFocus()
            }
        }
        touchEvent = if (ev?.action == MotionEvent.ACTION_MOVE) MotionEvent.ACTION_MOVE else null
        return super.dispatchTouchEvent(ev)
    }
}