package com.ardinata.feature_dashboard.landing.presenter

import androidx.lifecycle.viewModelScope
import com.ardinata.service_cocktail.domain.usecase.service.GetNowPlayingMovieUseCase
import com.ardinata.service_cocktail.domain.usecase.service.GetPopularMovieUseCase
import com.ardinata.service_cocktail.domain.usecase.service.GetTopRatedMovieUseCase
import com.ardinata.service_cocktail.domain.usecase.service.GetUpcomingMovieUseCase
import com.ardinata.test.wlb.core.base.BaseViewModel
import com.ardinata.test.wlb.core.extension.StatefulLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    getUpcomingMovieUseCase: GetUpcomingMovieUseCase,
    getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
    getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase,
    getPopularMovieUseCase: GetPopularMovieUseCase
) : BaseViewModel() {
    override fun getKillableStatefulLiveData(): List<StatefulLiveData<*, *>> {
        return listOf()
    }

    val upcomingMovies = StatefulLiveData(
        getUpcomingMovieUseCase,
        viewModelScope
    )

    val topRatedMovie = StatefulLiveData(
        getTopRatedMovieUseCase,
        viewModelScope
    )

    val nowPlayingMovie = StatefulLiveData(
        getNowPlayingMovieUseCase,
        viewModelScope
    )

    val popularMovie = StatefulLiveData(
        getPopularMovieUseCase,
        viewModelScope
    )

    init {
        upcomingMovies.execute("1")
        topRatedMovie.execute("1")
        nowPlayingMovie.execute("1")
        popularMovie.execute("1")
    }

}