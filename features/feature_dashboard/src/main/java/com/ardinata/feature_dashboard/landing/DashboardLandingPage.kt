package com.ardinata.feature_dashboard.landing

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PageDashboardLandingBinding
import com.ardinata.feature_dashboard.landing.mapper.TabsItemMapper
import com.ardinata.feature_dashboard.landing.pager_movie_list.adapter.DashboardPagerAdapter
import com.ardinata.feature_dashboard.landing.pager_favourite.PagerFavouriteList
import com.ardinata.feature_dashboard.landing.pager_movie_list.MovieList
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.feature_dashboard.landing.pager_search.SearchPager
import com.ardinata.feature_util.NetworkStateListener
import com.ardinata.test.test_goplay.core.base.BaseViewBindingFragment
import com.ardinata.test.test_goplay.template.TabsItem
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
        adapter = DashboardPagerAdapter(
            requireActivity(),
            MovieList.createInstance(MovieList.Companion.PageMode.MOVIE),
            MovieList.createInstance(MovieList.Companion.PageMode.TV),
            SearchPager(),
            PagerFavouriteList()
        )
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setupPager()
        setupListener()
        NetworkStateListener.listen(
            requireContext(),
            onAvailable = {
                lifecycleScope.launchWhenResumed { binding?.offlineNotice?.isVisible = false }
            },
            onLost = {
                lifecycleScope.launchWhenResumed { binding?.offlineNotice?.isVisible = true }
            }
        )
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