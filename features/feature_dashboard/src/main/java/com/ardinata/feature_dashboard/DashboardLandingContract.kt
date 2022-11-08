package com.ardinata.feature_dashboard

import android.content.Context
import com.ardinata.test.test_goplay.core.contract.RouterContract

interface DashboardLandingContract {
    interface Router : RouterContract {
        fun navigateToDashboard(context: Context)
    }
}