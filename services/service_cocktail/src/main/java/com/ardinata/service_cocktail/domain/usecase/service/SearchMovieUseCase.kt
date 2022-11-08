package com.ardinata.service_cocktail.domain.usecase.service

import com.ardinata.service_cocktail.domain.entity.MovieListEntity
import com.ardinata.service_cocktail.domain.entity.SearchMovieRequestEntity
import com.ardinata.service_cocktail.domain.repository.MovieDBServiceRepository
import com.ardinata.test.wlb.core.base.BaseUseCase
import com.ardinata.test.wlb.core.model.Result
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repo: MovieDBServiceRepository
) : BaseUseCase<SearchMovieRequestEntity, MovieListEntity>(){
    override val default: MovieListEntity
        get() = MovieListEntity.DEFAULT

    override suspend fun build(param: SearchMovieRequestEntity): Result<MovieListEntity> {
        return repo.searchTV(param)
    }
}