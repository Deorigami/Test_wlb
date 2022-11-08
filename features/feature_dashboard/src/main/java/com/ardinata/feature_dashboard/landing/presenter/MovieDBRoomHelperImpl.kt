package com.ardinata.feature_dashboard.landing.presenter

import com.ardinata.service_cocktail.domain.entity.MovieListItemEntity
import com.ardinata.test.wlb.core.base.BaseUseCase
import com.ardinata.test.wlb.core.extension.StatefulLiveData

class MovieDBRoomHelperImpl : MovieDBRoomHelper {
    override fun <P, R> insertMovieItem(
        item: P,
        useCase: StatefulLiveData<P, R>
    ) {
        useCase.getData(item)
    }
}