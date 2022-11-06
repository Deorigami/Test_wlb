package com.ardinata.feature_dashboard.landing.pager

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PagerDrinkListBinding
import com.ardinata.feature_dashboard.landing.modal.FilterModal
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.service_cocktail.domain.resource.MOVIESECTION
import com.ardinata.test.wlb.core.base.BaseViewBindingFragment
import com.ardinata.test.wlb.core.extension.textChanges
import com.ardinata.test.wlb.organism.CardItemView
import com.ardinata.test.wlb.organism.TabsCustomItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class PagerDrinkList(
    override val layout: Int = R.layout.pager_drink_list
) : BaseViewBindingFragment<PagerDrinkListBinding>() {

    private val viewModel by activityViewModels<DashboardViewModel>()

    @Inject
    override lateinit var router: DashboardLandingContract.Router

    lateinit var adapter : MovieListVPAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieFragments = MOVIESECTION.values().filter { it.name.lowercase().contains("movie") }.map { data ->
            MovieListPager(data)
        }.toMutableList()
        adapter = MovieListVPAdapter(
            childFragmentManager,
            lifecycle,
            *movieFragments.toTypedArray()
        )

    }

    override fun initBinding(view: View) {
        binding = PagerDrinkListBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        initObserver()
        binding?.categorySelector?.apply {
            items = MOVIESECTION.values().filter { it.name.lowercase().contains("movie") }.mapIndexed { index, data ->
                TabsCustomItem.Data(
                    data.text,
                    index == 0
                )
            }.toMutableList()
        }
        initView()
        setListener()
    }

    private fun setListener() {
        binding?.categorySelector?.apply {
            onPressed = {
                binding?.viewPager?.currentItem = it
            }
        }
    }

    private fun initView() {
        binding?.viewPager?.apply {
            adapter = this@PagerDrinkList.adapter
            currentItem = 0
            isUserInputEnabled = false
        }
    }

    private fun initObserver() {

    }
}