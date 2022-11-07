package com.ardinata.feature_dashboard.landing.presenter

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.ardinata.service_cocktail.domain.entity.MovieListItemEntity
import com.ardinata.service_cocktail.domain.entity.TVListItemEntity
import com.ardinata.service_cocktail.domain.usecase.service.*
import com.ardinata.test.wlb.core.base.BaseViewModel
import com.ardinata.test.wlb.core.extension.StatefulLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    // Movies UseCases
    getUpcomingMovieUseCase: GetUpcomingMovieUseCase,
    getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
    getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase,
    getPopularMovieUseCase: GetPopularMovieUseCase,

    // TV UseCases
    getAiringTodayTVUseCase: GetAiringTodayTVUseCase,
    getOnTheAirTVUseCase: GetOnTheAirTVUseCase,
    getPopularTVUseCase: GetPopularTVUseCase,
    getTopRatedTVUseCase: GetTopRatedTVUseCase
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

    val popularTV = StatefulLiveData(
        getPopularTVUseCase,
        viewModelScope
    )

    val topRatedTV = StatefulLiveData(
        getTopRatedTVUseCase,
        viewModelScope
    )

    val airingTodayTV = StatefulLiveData(
        getAiringTodayTVUseCase,
        viewModelScope
    )

    val onTheAirTV = StatefulLiveData(
        getOnTheAirTVUseCase,
        viewModelScope
    )

    // PAGINATED MOVIE

    val paginatedPopularMovie = MediatorLiveData<MutableList<MovieListItemEntity>?>().apply {
        fun update(){
            val newFetchedMovie = popularMovie.onSuccess.value?.results ?: emptyList()
            val newValue = object : ArrayList<MovieListItemEntity>() {
                init {
                    addAll(value ?: emptyList())
                    addAll(newFetchedMovie)
                }
            }
            value = newValue
        }
        addSource(popularMovie.onSuccess){ update() }
    }

    val paginatedUpcomingMovie = MediatorLiveData<MutableList<MovieListItemEntity>?>().apply {
        fun update(){
            val newFetchedMovie = upcomingMovies.onSuccess.value?.results ?: emptyList()
            val newValue = object : ArrayList<MovieListItemEntity>() {
                init {
                    addAll(value ?: emptyList())
                    addAll(newFetchedMovie)
                }
            }
            value = newValue
        }
        addSource(upcomingMovies.onSuccess){ update() }
    }

    val paginatedTopRatedMovie = MediatorLiveData<MutableList<MovieListItemEntity>?>().apply {
        fun update(){
            val newFetchedMovie = topRatedMovie.onSuccess.value?.results ?: emptyList()
            val newValue = object : ArrayList<MovieListItemEntity>() {
                init {
                    addAll(value ?: emptyList())
                    addAll(newFetchedMovie)
                }
            }
            value = newValue
        }
        addSource(topRatedMovie.onSuccess){ update() }
    }

    val paginatedNowPlayingMovie = MediatorLiveData<MutableList<MovieListItemEntity>?>().apply {
        fun update(){
            val newFetchedMovie = nowPlayingMovie.onSuccess.value?.results ?: emptyList()
            val newValue = object : ArrayList<MovieListItemEntity>() {
                init {
                    addAll(value ?: emptyList())
                    addAll(newFetchedMovie)
                }
            }
            value = newValue
        }
        addSource(nowPlayingMovie.onSuccess){ update() }
    }

    // PAGINATED SERIES

    val paginatedOnTheAirTV = MediatorLiveData<MutableList<TVListItemEntity>?>().apply {
        fun update(){
            val newFetchedMovie = onTheAirTV.onSuccess.value?.results ?: emptyList()
            val newValue = object : ArrayList<TVListItemEntity>() {
                init {
                    addAll(value ?: emptyList())
                    addAll(newFetchedMovie)
                }
            }
            value = newValue
        }
        addSource(onTheAirTV.onSuccess){ update() }
    }

    val paginatedAiringTodayTV = MediatorLiveData<MutableList<TVListItemEntity>?>().apply {
        fun update(){
            val newFetchedMovie = airingTodayTV.onSuccess.value?.results ?: emptyList()
            val newValue = object : ArrayList<TVListItemEntity>() {
                init {
                    addAll(value ?: emptyList())
                    addAll(newFetchedMovie)
                }
            }
            value = newValue
        }
        addSource(airingTodayTV.onSuccess){ update() }
    }

    val paginatedTopRatedTV = MediatorLiveData<MutableList<TVListItemEntity>?>().apply {
        fun update(){
            val newFetchedMovie = topRatedTV.onSuccess.value?.results ?: emptyList()
            val newValue = object : ArrayList<TVListItemEntity>() {
                init {
                    addAll(value ?: emptyList())
                    addAll(newFetchedMovie)
                }
            }
            value = newValue
        }
        addSource(topRatedTV.onSuccess){ update() }
    }

    val paginatedPopularTV = MediatorLiveData<MutableList<TVListItemEntity>?>().apply {
        fun update(){
            val newFetchedMovie = popularTV.onSuccess.value?.results ?: emptyList()
            val newValue = object : ArrayList<TVListItemEntity>() {
                init {
                    addAll(value ?: emptyList())
                    addAll(newFetchedMovie)
                }
            }
            value = newValue
        }
        addSource(popularTV.onSuccess){ update() }
    }

    // GET DATA MOVIES

    fun getPopularMovieList(){
        val data = popularMovie.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) popularMovie.getData(page.toString())
    }

    fun getNowPlayingMovieList(){
        val data = nowPlayingMovie.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) nowPlayingMovie.getData(page.toString())
    }

    fun getTopRatedMovieList(){
        val data = topRatedMovie.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) topRatedMovie.getData(page.toString())
    }

    fun getUpcomingMovieList(){
        val data = upcomingMovies.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) upcomingMovies.getData(page.toString())
    }

    // GET DATA SERIES

    fun getOnTheAirTVList(){
        val data = onTheAirTV.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) onTheAirTV.getData(page.toString())
    }

    fun getAiringTodayTVList(){
        val data = airingTodayTV.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) airingTodayTV.getData(page.toString())
    }

    fun getTopRatedTVList(){
        val data = topRatedTV.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) topRatedTV.getData(page.toString())
    }

    fun getPopularTVList(){
        val data = popularTV.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) popularTV.getData(page.toString())
    }

}