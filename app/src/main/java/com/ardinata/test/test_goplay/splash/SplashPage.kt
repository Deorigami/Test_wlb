package com.ardinata.test.test_goplay.splash

import android.view.View
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.test.test_goplay.R
import com.ardinata.test.test_goplay.core.base.BaseViewBindingFragment
import com.ardinata.test.test_goplay.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashPage(
    override val layout: Int = R.layout.fragment_main
) : BaseViewBindingFragment<FragmentMainBinding>() {

    @Inject override lateinit var router: DashboardLandingContract.Router

    override fun initBinding(view: View) {
        binding = FragmentMainBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
    }
}