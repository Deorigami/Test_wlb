package com.ardinata.feature_dashboard.landing.movie_list_pager

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PagerMovieListBinding
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.feature_util.NetworkStateListener
import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import com.ardinata.service_movie_db.domain.entity.TVListItemEntity
import com.ardinata.service_movie_db.domain.resource.MovieDBSection
import com.ardinata.test.test_goplay.core.base.BaseViewBindingFragment
import com.ardinata.test.test_goplay.core.contract.RouterContract
import com.ardinata.test.test_goplay.organism.CardItemView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieListPager(
    private val section: MovieDBSection,
    override val layout: Int = R.layout.pager_movie_list
) : BaseViewBindingFragment<PagerMovieListBinding>() {

    private val viewModel by viewModels<DashboardViewModel>()
    @Inject
    override lateinit var router: DashboardLandingContract.Router

    override fun initBinding(view: View) {
        binding = PagerMovieListBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setObserver()
        NetworkStateListener.listen(
            requireContext(),
            onAvailable = {
                viewModel.isNetworkAvailable.postValue(true)
                getData()
            },
            onLost = {
                viewModel.isNetworkAvailable.postValue(false)
                viewModel.getMovieRoom.getData(
                    Unit,
                    onSuccess = {
                        lifecycleScope.launchWhenResumed {
                            setMovieSection(viewModel.getOfflineList(section))
                        }
                    },
                    onComplete = {}
                )

            }
        )
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
            else -> Unit
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
                        title = it.title,
                        category = "",
                        "",
                        ""
                    )
                }.toMutableList()
                onFinishScrolling = { getData() }
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

    private fun setSeriesSection(list: List<TVListItemEntity>){
        lifecycleScope.launchWhenResumed {
            binding?.cardList?.apply {
                items = list.distinctBy { it.id }.map {
                    CardItemView.Data(
                        imagePoster = it.posterPath,
                        title = it.name,
                        category = "",
                        "",
                        ""
                    )
                }.toMutableList()
                onFinishScrolling = { getData() }
                onCardPressed = { idx ->
                    list.getOrNull(idx)?.let {
                        router.navigateToMovieDetailPage(
                            requireContext(),
                            tvListItemEntity = it
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun createInstance(section: MovieDBSection) : MovieListPager = MovieListPager(section)
    }
}