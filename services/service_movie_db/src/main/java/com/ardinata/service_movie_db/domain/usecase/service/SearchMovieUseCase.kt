package com.ardinata.service_movie_db.domain.usecase.service

import com.ardinata.service_movie_db.domain.entity.MovieListEntity
import com.ardinata.service_movie_db.domain.entity.SearchMovieRequestEntity
import com.ardinata.service_movie_db.domain.repository.MovieDBServiceRepository
import com.ardinata.test.test_goplay.core.base.BaseUseCase
import com.ardinata.test.test_goplay.core.model.Result
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