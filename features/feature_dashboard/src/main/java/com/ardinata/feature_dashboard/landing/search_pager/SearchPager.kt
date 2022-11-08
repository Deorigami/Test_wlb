package com.ardinata.feature_dashboard.landing.search_pager

import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PagerSearchBinding
import com.ardinata.feature_dashboard.landing.search_pager.presenter.SearchPagerViewModel
import com.ardinata.test.test_goplay.core.base.BaseViewBindingFragment
import com.ardinata.test.test_goplay.core.extension.textChanges
import com.ardinata.test.test_goplay.organism.CardItemView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SearchPager(
    override val layout: Int = R.layout.pager_search
): BaseViewBindingFragment<PagerSearchBinding>(){

    private val viewModel by viewModels<SearchPagerViewModel>()

    @Inject
    override lateinit var router : DashboardLandingContract.Router

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
            paginatedMoveSearchResult.observe(viewLifecycleOwner){
                binding?.searchNotFoundImage?.isVisible = it.isNullOrEmpty()
                it?.let {
                    binding?.searchInitImage?.isVisible = false
                    binding?.cardList?.apply {
                        items = it.map { CardItemView.Data(
                            imagePoster = "https://image.tmdb.org/t/p/w200${it.posterPath.ifEmpty { it.backdropPath }}",
                            title = it.title,
                            category = "",
                            "",
                            ""
                        ) }.toMutableList()
                        onFinishScrolling = {
                            searchMovie(currentSearchKeyWord.value){
                                Toast.makeText(
                                    requireContext(),
                                    requireContext().getString(R.string.data_already_loaded_notice),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        onCardPressed = { idx ->
                            it.getOrNull(idx)?.let {
                                router.navigateToMovieDetailPage(
                                    requireContext(),
                                    movieItem = it
                                )
                            }
                        }
                    }
                }
            }
            searchMovie.listen(
                viewLifecycleOwner,
                onStart = {
                    showLoading()
                },
                onSuccess = {
                    if (it.results.isEmpty()) {
                        showErrorModal()
                        binding?.searchNotFoundImage?.isVisible = true
                    }
                },
                onError = {
                    showErrorModal()
                    binding?.searchNotFoundImage?.isVisible = true
                },
                onComplete = {
                    closeLoading()
                }
            )
        }
    }

    @OptIn(FlowPreview::class)
    private fun setListener() {
        binding?.textField?.textField?.textChanges()?.debounce(1000)?.onEach {
            if (!it.isNullOrBlank()) viewModel.searchMovie(it.toString())
        }?.launchIn(lifecycleScope)
    }

    private fun showErrorModal(){
        showHalfModal(
            childFragmentManager,
            title = "Keyword Not Found",
            body = "Try Another Keyword to find Your Need",
            primaryButtonTitle = "OK"
        )
    }
}