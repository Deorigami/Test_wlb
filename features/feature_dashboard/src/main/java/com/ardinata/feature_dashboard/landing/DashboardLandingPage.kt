package com.ardinata.feature_dashboard.landing

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PageDashboardLandingBinding
import com.ardinata.feature_dashboard.landing.mapper.TabsItemMapper
import com.ardinata.feature_dashboard.landing.movie_list_pager.adapter.DashboardPagerAdapter
import com.ardinata.feature_dashboard.landing.movie_list_pager.PagerFavDrinkList
import com.ardinata.feature_dashboard.landing.movie_list_pager.MovieList
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.feature_dashboard.landing.search_pager.SearchPager
import com.ardinata.test.wlb.core.base.BaseViewBindingFragment
import com.ardinata.test.wlb.template.TabsItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardLandingPage(
    override val layout: Int = R.layout.page_dashboard_landing
) : BaseViewBindingFragment<PageDashboardLandingBinding>() {

    @Inject
    override lateinit var router: DashboardLandingContract.Router

    private val viewModel: DashboardViewModel by activityViewModels()
    private lateinit var adapter: DashboardPagerAdapter

    override fun initBinding(view: View) {
        binding = PageDashboardLandingBinding.bind(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = DashboardPagerAdapter(
            childFragmentManager,
            lifecycle,
            MovieList(MovieList.Companion.PageMode.MOVIE),
            MovieList(MovieList.Companion.PageMode.TV),
            SearchPager(),
            PagerFavDrinkList()
        )
    }

    override fun onResume() {
        super.onResume()
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setupPager()
        setupListener()
        viewModel.run {

        }
    }

    private fun setupPager() {
        binding?.viewPager?.apply {
            adapter = this@DashboardLandingPage.adapter
            isUserInputEnabled = false
        }
        TabLayoutMediator(
            binding?.tabs ?: return,
            binding?.viewPager ?: return
        ) { tabs, position ->
            tabs.customView = TabsItemMapper().invoke(requireContext(), position)
        }.attach()
    }

    private fun setupListener() {
        binding?.tabs?.apply {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    (tab?.customView as? TabsItem)?.isActive = true
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    (tab?.customView as? TabsItem)?.isActive = false
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // do nothing
                }
            })
        }
    }
}