package com.ardinata.feature_dashboard.landing.pager

import android.view.View
import androidx.fragment.app.viewModels
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PagerMovieListBinding
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.service_cocktail.domain.entity.MovieListItemEntity
import com.ardinata.service_cocktail.domain.entity.TVListItemEntity
import com.ardinata.service_cocktail.domain.resource.MovieDBSection
import com.ardinata.test.wlb.core.base.BaseViewBindingFragment
import com.ardinata.test.wlb.organism.CardItemView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListPager(
    private val section: MovieDBSection,
    override val layout: Int = R.layout.pager_movie_list
) : BaseViewBindingFragment<PagerMovieListBinding>() {

    private val viewModel by viewModels<DashboardViewModel>()

    override fun initBinding(view: View) {
        binding = PagerMovieListBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setObserver()
        getData()
    }

    private fun getData() {
        when(section){
            MovieDBSection.POPULAR_MOVIE -> viewModel.getPopularMovieList()
            MovieDBSection.NOW_PLAYING_MOVIE -> viewModel.getNowPlayingMovieList()
            MovieDBSection.TOP_RATED_MOVIE -> viewModel.getTopRatedMovieList()
            MovieDBSection.UPCOMING_MOVIE -> viewModel.getUpcomingMovieList()
            MovieDBSection.ON_THE_AIR_TV -> viewModel.getOnTheAirTVList()
            MovieDBSection.POPULAR_TV -> viewModel.getPopularTVList()
            MovieDBSection.TOP_RATED_TV -> viewModel.getTopRatedTVList()
            MovieDBSection.AIRING_TODAY_TV -> viewModel.getAiringTodayTVList()
        }
    }

    private fun setObserver() {
        viewModel.run {
            // Movie Observer
            paginatedPopularMovie.observe(viewLifecycleOwner) {
                it?.let { setMovieSection(it) }
            }
            paginatedNowPlayingMovie.observe(viewLifecycleOwner) {
                it?.let { setMovieSection(it) }
            }
            paginatedUpcomingMovie.observe(viewLifecycleOwner) {
                it?.let { setMovieSection(it) }
            }
            paginatedTopRatedMovie.observe(viewLifecycleOwner) {
                it?.let { setMovieSection(it) }
            }

            // Series Observer
            paginatedAiringTodayTV.observe(viewLifecycleOwner) {
                it?.let { setSeriesSection(it) }
            }
            paginatedPopularTV.observe(viewLifecycleOwner) {
                it?.let { setSeriesSection(it) }
            }
            paginatedTopRatedTV.observe(viewLifecycleOwner) {
                it?.let { setSeriesSection(it) }
            }
            paginatedOnTheAirTV.observe(viewLifecycleOwner) {
                it?.let { setSeriesSection(it) }
            }

            // Movie LifeCycle
            upcomingMovies.listen(viewLifecycleOwner)
            nowPlayingMovie.listen(viewLifecycleOwner)
            popularMovie.listen(viewLifecycleOwner)
            topRatedMovie.listen(viewLifecycleOwner)

            //Series LifeCycle
            upcomingMovies.listen(viewLifecycleOwner)
            nowPlayingMovie.listen(viewLifecycleOwner)
            popularMovie.listen(viewLifecycleOwner)
            topRatedMovie.listen(viewLifecycleOwner)
        }
    }

    private fun setMovieSection(
        list: List<MovieListItemEntity>,
    ) {
        binding?.cardList?.apply {
            items = list.distinctBy { it.id }.map {
                CardItemView.Data(
                    imagePoster = "https://image.tmdb.org/t/p/w200${it.posterPath.ifEmpty { it.backdropPath }}",
                    title = it.title,
                    category = "",
                    "",
                    ""
                )
            }.toMutableList()
            onFinishScrolling = { getData() }
        }
    }

    private fun setSeriesSection(list: List<TVListItemEntity>){
        binding?.cardList?.apply {
            items = list.distinctBy { it.id }.map {
                CardItemView.Data(
                    imagePoster = "https://image.tmdb.org/t/p/w200${it.posterPath.ifEmpty { it.backdropPath }}",
                    title = it.name,
                    category = "",
                    "",
                    ""
                )
            }.toMutableList()
            onFinishScrolling = { getData() }
        }
    }

    companion object {
        fun createInstance(section: MovieDBSection) : MovieListPager = MovieListPager(section)
    }
}