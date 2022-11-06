package com.ardinata.test.wlb.util

import android.content.Context
import android.os.Build
import android.widget.TextView

fun Int.convertDpToPx(context: Context): Float {
    return this * context.resources.displayMetrics.density
}

fun Int.dp(context: Context): Int {
    return this * context.resources.displayMetrics.density.toInt()
}

fun TextView.setCompatibleTextAppearance(style : Int){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this.setTextAppearance(style)
    } else {
        this.setTextAppearance(context, style)
    }
}