package com.ardinata.feature_dashboard.landing.pager_favourite

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PageFavouriteDrinkListBinding
import com.ardinata.feature_dashboard.landing.pager_favourite.presenter.FavouritePagerViewModel
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import com.ardinata.test.test_goplay.core.base.BaseViewBindingFragment
import com.ardinata.test.test_goplay.organism.CardItemView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PagerFavouriteList(
    override val layout: Int = R.layout.page_favourite_drink_list
) : BaseViewBindingFragment<PageFavouriteDrinkListBinding>(){

    private val viewModel : FavouritePagerViewModel by viewModels()

    @Inject
    override lateinit var router: DashboardLandingContract.Router

    override fun initBinding(view: View) {
        binding = PageFavouriteDrinkListBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed { viewModel.getFavouriteMovies.execute(Unit) }
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setObserver()
    }

    private fun setObserver() {
        viewModel.run {
            getFavouriteMovies.listen(
                viewLifecycleOwner,
                onSuccess = { 
                    setMovieSection(it)
                }
            )
        }
    }

    private fun setMovieSection(
        list: List<MovieListItemEntity>,
    ) {
        lifecycleScope.launchWhenResumed {
            binding?.cardList?.apply {
                items = list.distinctBy { it.id }.map {
                    CardItemView.Data(
                        imagePoster = it.posterPath,
                        title = it.title
                    )
                }.toMutableList()
                onCardPressed = { idx ->
                    list.getOrNull(idx)?.let {
                        router.navigateToMovieDetailPage(
                            requireContext(),
                            movieItem = it
                        )
                    }
                }
            }
        }
    }
}