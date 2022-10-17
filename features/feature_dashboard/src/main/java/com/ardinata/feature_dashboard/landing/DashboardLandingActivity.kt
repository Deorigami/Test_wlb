package com.ardinata.feature_dashboard.landing

import android.os.Bundle
import com.ardinata.feature_dashboard.R
import com.ardinata.test.wlb.core.base.BaseActivity

class DashboardLandingActivity(
    layout: Int = R.layout.base_activity_layout
) : BaseActivity(layout){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity_layout)

        val fragment = DashboardLandingPage()
        fragment.arguments = intent.extras
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()
    }
}