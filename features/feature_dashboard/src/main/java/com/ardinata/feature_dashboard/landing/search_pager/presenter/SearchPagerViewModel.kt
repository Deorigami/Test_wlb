package com.ardinata.feature_dashboard.landing.search_pager.presenter

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.ardinata.service_cocktail.domain.entity.MovieListItemEntity
import com.ardinata.service_cocktail.domain.entity.SearchMovieRequestEntity
import com.ardinata.service_cocktail.domain.usecase.service.SearchMovieUseCase
import com.ardinata.test.wlb.core.base.BaseViewModel
import com.ardinata.test.wlb.core.extension.NonNullMutableLiveData
import com.ardinata.test.wlb.core.extension.StatefulLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchPagerViewModel @Inject constructor(
    searchMovieUseCase: SearchMovieUseCase
) : BaseViewModel(){
    override fun getKillableStatefulLiveData(): List<StatefulLiveData<*, *>> {
        return listOf()
    }

    val currentSearchKeyWord = NonNullMutableLiveData("")
    val isSameKeyword = NonNullMutableLiveData(false)

    val searchMovie = StatefulLiveData(
        searchMovieUseCase,
        viewModelScope
    )

    val paginatedMoveSearchResult = MediatorLiveData<List<MovieListItemEntity>?>().apply {
        fun update(){
            val justFetchedResult = searchMovie.onSuccess.value?.results ?: emptyList()
            val newValue = object : ArrayList<MovieListItemEntity>() {
                init {
                    if (isSameKeyword.value) addAll(value ?: emptyList())
                    addAll(justFetchedResult)
                }
            }
            value = newValue
        }
        addSource(searchMovie.onSuccess){ update() }
    }

    fun searchMovie(
        keyword: String,
        onLastPage : () -> Unit = {}
    ){
        isSameKeyword.value = currentSearchKeyWord.value == keyword
        if (!isSameKeyword.value) currentSearchKeyWord.value = keyword
        val searchMovieData = searchMovie.onSuccess.value
        val page = if (isSameKeyword.value) searchMovieData?.page?.plus(1) else 1
        val alreadyLastPage = (searchMovieData?.page ?: 1) == searchMovieData?.totalPages && isSameKeyword.value
        if (!alreadyLastPage) {
            searchMovie.getData(
                SearchMovieRequestEntity(
                    page.toString(),
                    currentSearchKeyWord.value
                )
            )
        } else {
            onLastPage.invoke()
        }
    }
}