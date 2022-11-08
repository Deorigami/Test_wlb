package com.ardinata.feature_util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi

object NetworkStateListener {
    fun listen(
        context: Context,
        onAvailable: () -> Unit = {},
        onLost: () -> Unit = {}
    ){
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                onAvailable.invoke()
            }

            override fun onLost(network: Network) {
                onLost.invoke()
            }

            override fun onUnavailable() {
                onLost.invoke()
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
            if (connectivityManager.activeNetwork == null){
                onLost.invoke()
            }
        } else {
            val networkRequest = NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
            connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        }
    }
}