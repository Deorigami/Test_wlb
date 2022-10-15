package com.ardinata.feature_dashboard.landing

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PageDashboardLandingBinding
import com.ardinata.feature_dashboard.landing.pager.DashboardPagerAdapter
import com.ardinata.feature_dashboard.landing.pager.PagerFavGameList
import com.ardinata.feature_dashboard.landing.pager.PagerGameList
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
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
            PagerGameList(),
            PagerFavGameList()
        )
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setupPager()
        setupListener()
        viewModel.run {
            categoriesFilter.listen(viewLifecycleOwner)
            glassesFilter.listen(viewLifecycleOwner)
            alcoholicFilter.listen(viewLifecycleOwner)
            cocktailList.listen(
                viewLifecycleOwner,
                onSuccess = {

                }
            )
            searchCocktail.listen(
                viewLifecycleOwner,
                onSuccess = {
                }
            )
            filterData.observe(viewLifecycleOwner){

            }
        }
    }

    private fun setupPager() {
        binding?.viewPager?.apply {
            adapter = this@DashboardLandingPage.adapter
        }
        TabLayoutMediator(
            binding?.tabs ?: return,
            binding?.viewPager ?: return
        ) { tabs, position ->
            tabs.customView = TabsItem(requireContext()).apply {
                when (position) {
                    0 -> data = TabsItem.Data(
                        activeIcon = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_home
                        ),
                        inactiveIcon = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_outline_home
                        ),
                        activeColor = ContextCompat.getColor(requireContext(), R.color.brightSkyBlue),
                        inactiveColor = ContextCompat.getColor(
                            requireContext(),
                            R.color.battleshipGrey
                        ),
                        title = "Home"
                    ).apply {
                        isActive = true
                    }
                    1 -> data = TabsItem.Data(
                        activeIcon = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_heart_filled
                        ),
                        inactiveIcon = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_heart_base_black
                        ),
                        activeColor = ContextCompat.getColor(requireContext(), R.color.brightSkyBlue),
                        inactiveColor = ContextCompat.getColor(
                            requireContext(),
                            R.color.battleshipGrey
                        ),
                        title = "Favorite",
                        shouldTintActiveIcon = false
                    )
                    else -> {}
                }
            }
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

                }
            })
        }
    }
}