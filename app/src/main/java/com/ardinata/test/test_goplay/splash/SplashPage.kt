package com.ardinata.test.test_goplay.splash

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.test.test_goplay.R
import com.ardinata.test.test_goplay.core.base.BaseViewBindingFragment
import com.ardinata.test.test_goplay.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashPage(
    override val layout: Int = R.layout.fragment_main
) : BaseViewBindingFragment<FragmentMainBinding>() {

    @Inject override lateinit var router: SplashContract.Router

    override fun initBinding(view: View) {
        binding = FragmentMainBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        lifecycleScope.launchWhenResumed {
            delay(2000)
            router.navigateToDashboard(requireContext())
            requireActivity().finish()
        }
    }
}