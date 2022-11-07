package com.ardinata.service_cocktail.domain.usecase.local

import com.ardinata.service_cocktail.domain.entity.MovieListItemEntity
import com.ardinata.service_cocktail.domain.repository.MovieDBRoomRepository
import com.ardinata.service_cocktail.domain.resource.MovieDBSection
import com.ardinata.test.wlb.core.base.BaseUseCase
import com.ardinata.test.wlb.core.model.Result
import javax.inject.Inject

class GetRoomMovieItemUseCase @Inject constructor(
    private val roomRepo: MovieDBRoomRepository
) : BaseUseCase<MovieDBSection, List<MovieListItemEntity>>(){
    override val default: List<MovieListItemEntity>
        get() = emptyList()

    override suspend fun build(param: MovieDBSection): Result<List<MovieListItemEntity>> {
        return roomRepo.getMovieBySection(param)
    }


}