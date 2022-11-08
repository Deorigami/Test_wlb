package com.ardinata.test.test_goplay.router.dashboard

import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import com.ardinata.feature_dashboard.landing.DashboardLandingActivity
import com.ardinata.test.test_goplay.core.base.BaseRouter
import com.ardinata.feature_util.util.GeneralRouter

abstract class GeneralRouterImpl : BaseRouter(), GeneralRouter {
    override fun navigateToDashboard(context: Context) {
        val i = Intent(context, DashboardLandingActivity::class.java).putExtras(bundleOf(

        ))
        context.startActivity(i)
    }
}