package com.ardinata.feature_dashboard.landing.pager

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PageFavouriteDrinkListBinding
import com.ardinata.feature_dashboard.landing.mapper.CocktailDrinkEntityMapper
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.test.wlb.core.base.BaseViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PagerFavDrinkList(
    override val layout: Int = R.layout.page_favourite_drink_list
) : BaseViewBindingFragment<PageFavouriteDrinkListBinding>(){

    private val viewModel : DashboardViewModel by activityViewModels()

    @Inject
    override lateinit var router: DashboardLandingContract.Router

    override fun initBinding(view: View) {
        binding = PageFavouriteDrinkListBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setObserver()
    }

    private fun setObserver() {
        viewModel.run {
            favoriteDrink.listen(
                viewLifecycleOwner,
                onSuccess = { list ->
                    Log.d("ANGGATAG", "$list")
                    binding?.cardGameList?.apply {
                        this.items = CocktailDrinkEntityMapper().invoke(list)
                        onCardPressed = { idx ->
                            val drink = list.getOrNull(idx)
                            drink?.let { router.navigateToGameDetail(requireContext(), it) }
                        }
                    }
                }
            )
        }
    }
}