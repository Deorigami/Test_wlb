package com.ardinata.feature_dashboard.detail

import android.view.View
import androidx.fragment.app.viewModels
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PageDrinkDetailBinding
import com.ardinata.feature_dashboard.detail.presenter.GameDetailViewModel
import com.ardinata.test.test_goplay.core.base.BaseViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrinkDetailPage(
    override val layout: Int = R.layout.page_drink_detail
) : BaseViewBindingFragment<PageDrinkDetailBinding>() {

    private val viewModel by viewModels<GameDetailViewModel>()

    override fun initBinding(view: View) {
        binding = PageDrinkDetailBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setObserver()
        initView()
    }

    private fun initView() {
        binding?.simpleHeader?.apply {
            onBackPressed = { activity?.onBackPressed() }
            onFavButtonChangeListener = { isFav ->

            }
        }
    }

    private fun setObserver() {
        viewModel.run {

        }
    }



    companion object {
        const val DRINK_ITEM = "DRINK_ITEM"
        const val IS_FAV_RESULT = "IS_FAV_RESULT"
    }
}