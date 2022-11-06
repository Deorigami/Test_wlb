package com.ardinata.feature_dashboard

import android.content.Context
import com.ardinata.test.wlb.core.contract.RouterContract

interface DashboardLandingContract {
    interface Router : RouterContract {
        fun navigateToDashboard(context: Context)
    }
}