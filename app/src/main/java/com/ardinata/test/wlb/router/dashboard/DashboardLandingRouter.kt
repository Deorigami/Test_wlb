package com.ardinata.test.wlb.router.dashboard

import android.content.Context
import android.content.Intent
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.landing.DashboardLandingActivity
import com.ardinata.test.wlb.core.base.BaseRouter
import javax.inject.Inject

class DashboardLandingRouter @Inject constructor() : BaseRouter(), DashboardLandingContract.Router {

    override fun navigateToDashboard(context: Context) {
        val intent = Intent(context, DashboardLandingActivity::class.java)
        context.startActivity(intent)
    }
}