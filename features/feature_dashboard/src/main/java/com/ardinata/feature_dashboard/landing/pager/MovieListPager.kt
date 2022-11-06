package com.ardinata.feature_dashboard.landing.pager

import android.view.View
import androidx.fragment.app.activityViewModels
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PagerMovieListBinding
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.service_cocktail.domain.resource.MOVIESECTION
import com.ardinata.test.wlb.core.base.BaseViewBindingFragment
import com.ardinata.test.wlb.organism.CardItemView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListPager(
    private val section: MOVIESECTION,
    override val layout: Int = R.layout.pager_movie_list
) : BaseViewBindingFragment<PagerMovieListBinding>(){

    private val viewModel by activityViewModels<DashboardViewModel>()

    override fun initBinding(view: View) {
        binding = PagerMovieListBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setObserver()
    }

    private fun setObserver() {
        viewModel.run {
            upcomingMovies.listen(
                viewLifecycleOwner,
                onSuccess = {
                    if (section == MOVIESECTION.UPCOMING_MOVIE) {
                        binding?.cardList?.items = it.results.map { CardItemView.Data(
                            imagePoster = "https://image.tmdb.org/t/p/w300${it.posterPath}",
                            title = it.title,
                            category = "",
                            "",
                            ""
                        ) }.toMutableList()
                    }
                }
            )
            nowPlayingMovie.listen(
                viewLifecycleOwner,
                onSuccess = {
                    if (section == MOVIESECTION.NOW_PLAYING_MOVIE) {
                        binding?.cardList?.items = it.results.map { CardItemView.Data(
                            imagePoster = "https://image.tmdb.org/t/p/w300${it.posterPath}",
                            title = it.title,
                            category = "",
                            "",
                            ""
                        ) }.toMutableList()
                    }
                }
            )
            popularMovie.listen(
                viewLifecycleOwner,
                onSuccess = {
                    if (section == MOVIESECTION.POPULAR_MOVIE) {
                        binding?.cardList?.items = it.results.map { CardItemView.Data(
                            imagePoster = "https://image.tmdb.org/t/p/w300${it.posterPath}",
                            title = it.title,
                            category = "",
                            "",
                            ""
                        ) }.toMutableList()
                    }
                }
            )
            topRatedMovie.listen(
                viewLifecycleOwner,
                onSuccess = {
                    if (section == MOVIESECTION.TOP_RATED_MOVIE) {
                        binding?.cardList?.items = it.results.map { CardItemView.Data(
                            imagePoster = "https://image.tmdb.org/t/p/w300${it.posterPath}",
                            title = it.title,
                            category = "",
                            "",
                            ""
                        ) }.toMutableList()
                    }
                }
            )
        }
    }
}