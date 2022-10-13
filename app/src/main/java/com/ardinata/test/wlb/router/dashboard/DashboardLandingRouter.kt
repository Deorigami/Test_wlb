package com.ardinata.test.wlb.router.dashboard

import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import com.ardinata.test.wlb.core.base.BaseRouter
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.detail.GameDetailActivity
import com.ardinata.feature_dashboard.detail.GameDetailPage
import com.ardinata.feature_dashboard.landing.DashboardLandingActivity
import javax.inject.Inject

class DashboardLandingRouter @Inject constructor() : BaseRouter(), DashboardLandingContract.Router {

    override fun navigateToDashboard(context: Context) {
        val intent = Intent(context, DashboardLandingActivity::class.java)
        context.startActivity(intent)
    }

    override fun navigateToGameDetail(context: Context, id: Long) {
        val intent = Intent(context, GameDetailActivity::class.java)
        intent.putExtras(bundleOf(
            GameDetailPage.GAME_ID to id
        ))
        context.startActivity(intent)
    }
}