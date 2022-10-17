package com.ardinata.feature_dashboard.landing.pager

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PagerGameListBinding
import com.ardinata.feature_dashboard.landing.mapper.CocktailDrinkEntityMapper
import com.ardinata.feature_dashboard.landing.modal.FilterModal
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.test.wlb.core.base.BaseViewBindingFragment
import com.ardinata.test.wlb.core.extension.textChanges
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class PagerGameList(
    override val layout: Int = R.layout.pager_game_list
) : BaseViewBindingFragment<PagerGameListBinding>() {

    private val viewModel by activityViewModels<DashboardViewModel>()
    @Inject
    override lateinit var router: DashboardLandingContract.Router

    override fun initBinding(view: View) {
        binding = PagerGameListBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        initObserver()
        setListener()
        showLoading()
    }

    private fun initObserver() {
        viewModel.run {
            cocktailList.listen(
                viewLifecycleOwner,
                onStart = { binding?.githubUserCards?.onFinishScrolling = {} },
                onSuccess = { shownListDataSource.value = FilteredListDataSource.GENERAL },
                onError = { showGetGeneralListErrorModal() },
                onComplete = this@PagerGameList::closeLoading
            )
            searchCocktail.listen(
                viewLifecycleOwner,
                onSuccess = {
                    shownListDataSource.value = FilteredListDataSource.SEARCH
                },
                onError = {
                    showHalfModal(
                        fragmentManager = childFragmentManager,
                        title = "Pencarian Tidak Ditemukan",
                        body = "Cobalah mencari dengan keyword lain",
                        primaryButtonTitle = "OK"
                    )
                }
            )
            filteredCocktailList.observe(viewLifecycleOwner, this@PagerGameList::handleFilteredList)
        }
    }

    private fun handleFilteredList(list: List<CocktailDrinkItemEntity>) {
        binding?.textFieldView?.apply {
            hasRightItem = true
            isRightItemActive = viewModel.hasFilter()
        }
        binding?.emptyState?.isVisible = list.isEmpty() && viewModel.hasFilter()
        setCardList(list)
    }

    @OptIn(FlowPreview::class)
    private fun setListener() {
        binding?.textFieldView?.apply {
            textField
                .textChanges()
                .debounce(500)
                .onEach {
                    when {
                        it.toString().isNotEmpty() -> viewModel.searchCocktail(it.toString())
                        else -> viewModel.shownListDataSource.value = FilteredListDataSource.GENERAL
                    }
                }
                .launchIn(lifecycleScope)
            onRightItemPressed = {
                FilterModal().show(childFragmentManager, "")
            }
        }
    }

    private fun setCardList(list: List<CocktailDrinkItemEntity>) {
        binding?.githubUserCards?.apply {
            this.items = CocktailDrinkEntityMapper().invoke(list)
            onCardPressed = {
                val drink = list.getOrNull(it)
                drink?.let {
                    router.navigateToGameDetail(requireContext(), drink)
                }
            }
            onFinishScrolling = {
                viewModel.getPaginatedCocktailList()
            }
        }
    }

    private fun showGetGeneralListErrorModal(){
        showHalfModal(
            childFragmentManager,
            "Sesuatu yang terjadi",
            "Terjadi kesalahan pada server",
            primaryButtonTitle = "Ulangi",
            onPrimaryButtonPressed = { viewModel.getPaginatedCocktailList() }
        )
    }


    companion object {
        enum class FilteredListDataSource {
            GENERAL, SEARCH
        }
    }
}