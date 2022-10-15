package com.ardinata.feature_dashboard.detail

import android.view.View
import androidx.fragment.app.viewModels
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PageGameDetailBinding
import com.ardinata.feature_dashboard.detail.presenter.GameDetailViewModel
import com.ardinata.test.wlb.core.base.BaseViewBindingFragment
import com.ardinata.test.wlb.util.customSetImage
import com.eyedea.service_cocktail.domain.entity.CocktailDrinkItemEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailPage(
    override val layout: Int = R.layout.page_game_detail
) : BaseViewBindingFragment<PageGameDetailBinding>() {

    private val viewModel by viewModels<GameDetailViewModel>()

    override fun initBinding(view: View) {
        binding = PageGameDetailBinding.bind(view)
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
        getDrink()?.let {
            binding?.apply {
                poster.customSetImage(it.drinkThumb)
                gameTitle.text = it.drink
            }
        }
    }

    private fun setObserver() {
        viewModel.run {

        }
    }

    private fun getDrink() = arguments?.get(DRINK_ITEM) as? CocktailDrinkItemEntity

    companion object {
        const val DRINK_ITEM = "DRINK_ITEM"
        const val IS_FAV_RESULT = "IS_FAV_RESULT"
    }
}