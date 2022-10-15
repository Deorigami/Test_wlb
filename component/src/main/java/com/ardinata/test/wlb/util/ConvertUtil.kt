package com.ardinata.test.wlb.util

import android.content.Context

fun Int.convertDpToPx(context: Context): Float {
    return this * context.resources.displayMetrics.density
}

fun Int.dp(context: Context): Int {
    return this * context.resources.displayMetrics.density.toInt()
}