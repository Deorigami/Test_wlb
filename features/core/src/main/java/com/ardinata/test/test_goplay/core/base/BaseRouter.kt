package com.ardinata.test.test_goplay.core.base

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.ardinata.core.R
import com.ardinata.test.test_goplay.core.contract.RouterContract
import java.lang.Exception

abstract class BaseRouter : RouterContract {

    private var navController : NavController ? = null
    private val navBuilder = NavOptions.Builder()

    override fun initiate(fragment: Fragment, isTransitionFromBottom: Boolean) {
        navController = try {
            navBuilder
                .setLaunchSingleTop(true)
                .setEnterAnim(R.anim.slide_in_right).setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left).setPopExitAnim(R.anim.slide_out_right)
            NavHostFragment.findNavController(fragment)
        } catch (ex : Exception){
            ex.printStackTrace()
            null
        }
    }

    protected fun push(
        navResource: Int,
        param: Bundle? = null,
        onNullNavController: ((navResource: Int, param: Bundle?) -> Unit)? = null
    ) {
        if (navController != null) {
            try {
                val action = navController?.currentDestination?.getAction(navResource)
                if (action != null || navController?.currentDestination?.id != navResource)
                    if (param != null && param.getBoolean("isNoNeedNavBuilder")) {
                        navController?.navigate(navResource, param)
                    } else {
                        navController?.navigate(navResource, param, navBuilder.build())
                    }
                else Log.e("ERROR", "destinationId: $navResource is unknown")
            } catch (e: Exception) {
                e.printStackTrace()
                onNullNavController?.invoke(navResource, param)
            }
        } else onNullNavController?.invoke(navResource, param)
    }
}