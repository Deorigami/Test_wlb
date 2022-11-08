package com.ardinata.service_movie_db.domain.usecase.local

import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import com.ardinata.service_movie_db.domain.repository.MovieDBRoomRepository
import com.ardinata.test.test_goplay.core.base.BaseUseCase
import com.ardinata.test.test_goplay.core.model.Result
import javax.inject.Inject

class InsertFavouriteMovieUseCase @Inject constructor(
    private val repo: MovieDBRoomRepository
) : BaseUseCase<MovieListItemEntity, Long>(){
    override val default: Long
        get() = 1

    override suspend fun build(param: MovieListItemEntity): Result<Long> {
        return repo.insertFavouriteMovie(param)
    }
}