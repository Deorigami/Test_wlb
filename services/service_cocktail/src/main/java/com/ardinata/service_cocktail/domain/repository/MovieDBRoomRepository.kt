package com.ardinata.service_cocktail.domain.repository

import com.ardinata.service_cocktail.domain.entity.MovieListItemEntity
import com.ardinata.service_cocktail.domain.resource.MovieDBSection
import com.ardinata.test.wlb.core.model.Result

interface MovieDBRoomRepository {
    suspend fun insertMovieItem(item: List<MovieListItemEntity>) : Result<Long>
    suspend fun getMovieBySection() : Result<List<MovieListItemEntity>>
}