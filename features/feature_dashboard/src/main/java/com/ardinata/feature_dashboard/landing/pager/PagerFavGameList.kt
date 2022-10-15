package com.ardinata.feature_dashboard.landing.pager

import android.view.View
import androidx.fragment.app.activityViewModels
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PageFavouriteGameListBinding
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.test.wlb.core.base.BaseViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PagerFavGameList(
    override val layout: Int = R.layout.page_favourite_game_list
) : BaseViewBindingFragment<PageFavouriteGameListBinding>(){

    private val viewModel : DashboardViewModel by activityViewModels()

    @Inject
    override lateinit var router: DashboardLandingContract.Router

    override fun initBinding(view: View) {
        binding = PageFavouriteGameListBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setObserver()
    }

    private fun setObserver() {
        viewModel.run {

        }
    }
}