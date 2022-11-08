package com.ardinata.service_cocktail.domain.usecase.local

import com.ardinata.service_cocktail.domain.entity.MovieListItemEntity
import com.ardinata.service_cocktail.domain.repository.MovieDBRoomRepository
import com.ardinata.test.wlb.core.base.BaseUseCase
import com.ardinata.test.wlb.core.model.Result
import javax.inject.Inject

class InsertRoomMovieItemUseCase @Inject constructor(
    private val roomRepo: MovieDBRoomRepository
) : BaseUseCase<List<MovieListItemEntity>, Long>(){

    override suspend fun build(param: List<MovieListItemEntity>): Result<Long> {
        return roomRepo.insertMovieItem(param)
    }

    override val default: Long
        get() = 0L

}