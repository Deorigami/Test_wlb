package com.ardinata.feature_dashboard.detail.presenter

import androidx.lifecycle.viewModelScope
import com.ardinata.service_movie_db.domain.usecase.service.GetMovieCastUseCase
import com.ardinata.service_movie_db.domain.usecase.service.GetTVCastUseCase
import com.ardinata.test.test_goplay.core.base.BaseViewModel
import com.ardinata.test.test_goplay.core.extension.StatefulLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    getMovieCastUseCase: GetMovieCastUseCase,
    getTVCastUseCase: GetTVCastUseCase
) : BaseViewModel(){
    override fun getKillableStatefulLiveData(): List<StatefulLiveData<*, *>> {
        return listOf()
    }

    val getMovieCast = StatefulLiveData(
        getMovieCastUseCase,
        viewModelScope
    )

    val getTvCast = StatefulLiveData(
        getTVCastUseCase,
        viewModelScope
    )

    init {

    }
}