package com.ardinata.feature_dashboard.landing.search_pager

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PagerSearchBinding
import com.ardinata.feature_dashboard.landing.search_pager.presenter.SearchPagerViewModel
import com.ardinata.test.wlb.core.base.BaseViewBindingFragment
import com.ardinata.test.wlb.core.extension.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchPager(
    override val layout: Int = R.layout.pager_search
): BaseViewBindingFragment<PagerSearchBinding>(){

    private val viewModel by viewModels<SearchPagerViewModel>()

    override fun initBinding(view: View) {
        binding = PagerSearchBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setListener()
        setObserver()
    }

    private fun setObserver() {
        viewModel.run {

        }
    }

    @OptIn(FlowPreview::class)
    private fun setListener() {
        binding?.textField?.textField?.textChanges()?.debounce(300)?.onEach {

        }?.launchIn(lifecycleScope)
    }


}