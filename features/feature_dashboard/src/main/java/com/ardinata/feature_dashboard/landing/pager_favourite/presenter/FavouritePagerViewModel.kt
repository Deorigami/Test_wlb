package com.ardinata.feature_dashboard.landing.pager_favourite.presenter

import com.ardinata.service_movie_db.domain.usecase.local.GetFavouriteMovieUseCase
import com.ardinata.test.test_goplay.core.base.BaseViewModel
import com.ardinata.test.test_goplay.core.extension.StatefulLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class FavouritePagerViewModel @Inject constructor(
    getFavouriteMovieUseCase: GetFavouriteMovieUseCase
) : BaseViewModel() {
    override fun getKillableStatefulLiveData(): List<StatefulLiveData<*, *>> {
        return listOf()
    }

    val getFavouriteMovies = StatefulLiveData(
        getFavouriteMovieUseCase,
        CoroutineScope(Dispatchers.IO)
    )
}