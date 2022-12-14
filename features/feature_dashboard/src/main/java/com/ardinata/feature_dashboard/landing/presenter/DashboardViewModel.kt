package com.ardinata.feature_dashboard.landing.presenter

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import com.ardinata.service_movie_db.domain.entity.TVListItemEntity
import com.ardinata.service_movie_db.domain.resource.MovieDBSection
import com.ardinata.service_movie_db.domain.usecase.local.GetRoomMovieItemUseCase
import com.ardinata.service_movie_db.domain.usecase.local.InsertRoomMovieItemUseCase
import com.ardinata.service_movie_db.domain.usecase.service.*
import com.ardinata.test.test_goplay.core.base.BaseViewModel
import com.ardinata.test.test_goplay.core.extension.NonNullMutableLiveData
import com.ardinata.test.test_goplay.core.extension.StatefulLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    getTopRatedTVUseCase: GetTopRatedTVUseCase,

    // RoomDB Movie UseCase
    insertRoomMovieItemUseCase: InsertRoomMovieItemUseCase,
    getRoomMovieItemUseCase: GetRoomMovieItemUseCase
) : BaseViewModel(){

    val isNetworkAvailable = NonNullMutableLiveData(true)

    override fun getKillableStatefulLiveData(): List<StatefulLiveData<*, *>> {
        return listOf(
            upcomingMovies,
            topRatedMovie,
            nowPlayingMovie,
            popularMovie,
            popularTV,
            topRatedMovie,
            onTheAirTV,
            airingTodayTV,
            insertMovieRoom,
            getMovieRoom
        )
    }

    private val upcomingMovies = StatefulLiveData(
        getUpcomingMovieUseCase,
        viewModelScope
    )

    private val topRatedMovie = StatefulLiveData(
        getTopRatedMovieUseCase,
        viewModelScope
    )

    private val nowPlayingMovie = StatefulLiveData(
        getNowPlayingMovieUseCase,
        viewModelScope
    )

    private val popularMovie = StatefulLiveData(
        getPopularMovieUseCase,
        viewModelScope
    )

    private val popularTV = StatefulLiveData(
        getPopularTVUseCase,
        viewModelScope
    )

    private val topRatedTV = StatefulLiveData(
        getTopRatedTVUseCase,
        viewModelScope
    )

    private val airingTodayTV = StatefulLiveData(
        getAiringTodayTVUseCase,
        viewModelScope
    )

    private val onTheAirTV = StatefulLiveData(
        getOnTheAirTVUseCase,
        viewModelScope
    )

    private val insertMovieRoom = StatefulLiveData(
        insertRoomMovieItemUseCase,
        CoroutineScope(Dispatchers.IO)
    )

    val getMovieRoom = StatefulLiveData(
        getRoomMovieItemUseCase,
        CoroutineScope(Dispatchers.IO)
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
            insertMovieRoom.executeLocally(newFetchedMovie.map { it.copy(
                page = popularMovie.onSuccess.value?.page?.toLong() ?: 1L,
                section = MovieDBSection.POPULAR_MOVIE
            ) })
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
            insertMovieRoom.executeLocally(newFetchedMovie.map { it.copy(
                page = upcomingMovies.onSuccess.value?.page?.toLong() ?: 1L,
                section = MovieDBSection.UPCOMING_MOVIE
            ) })
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
            insertMovieRoom.executeLocally(newFetchedMovie.map { it.copy(
                page = topRatedMovie.onSuccess.value?.page?.toLong() ?: 1L,
                section = MovieDBSection.TOP_RATED_MOVIE
            ) })
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
            insertMovieRoom.executeLocally(newFetchedMovie.map { it.copy(
                page = nowPlayingMovie.onSuccess.value?.page?.toLong() ?: 1L,
                section = MovieDBSection.NOW_PLAYING_MOVIE
            ) })
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

    init {
        getMovieRoom.executeLocally(Unit)
    }

    // GET DATA MOVIES

    fun getPopularMovieList(){
        val data = popularMovie.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) popularMovie.executeLocally(page.toString())
    }

    fun getNowPlayingMovieList(){
        val data = nowPlayingMovie.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) nowPlayingMovie.executeLocally(page.toString())
    }

    fun getTopRatedMovieList(){
        val data = topRatedMovie.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) topRatedMovie.executeLocally(page.toString())
    }

    fun getUpcomingMovieList(){
        val data = upcomingMovies.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) upcomingMovies.executeLocally(page.toString())
    }

    // GET DATA SERIES

    fun getOnTheAirTVList(){
        val data = onTheAirTV.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) onTheAirTV.executeLocally(page.toString())
    }

    fun getAiringTodayTVList(){
        val data = airingTodayTV.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) airingTodayTV.executeLocally(page.toString())
    }

    fun getTopRatedTVList(){
        val data = topRatedTV.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) topRatedTV.executeLocally(page.toString())
    }

    fun getPopularTVList(){
        val data = popularTV.onSuccess.value
        val page = data?.page?.plus(1) ?: 1
        if (page != data?.totalPages) popularTV.executeLocally(page.toString())
    }

    fun getOfflineList(section: MovieDBSection) : List<MovieListItemEntity> {
        val data = getMovieRoom.onSuccess.value?.apply {
        }?.filter { it.section == section }
        return data ?: emptyList()
    }

}