package com.ardinata.service_movie_db.domain.usecase.local

import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import com.ardinata.service_movie_db.domain.repository.MovieDBRoomRepository
import com.ardinata.test.test_goplay.core.base.BaseUseCase
import com.ardinata.test.test_goplay.core.model.Result
import javax.inject.Inject

class GetFavouriteMovieUseCase @Inject constructor(
    private val repo: MovieDBRoomRepository
) : BaseUseCase<Unit, List<MovieListItemEntity>>(){
    override val default: List<MovieListItemEntity>
        get() = emptyList()

    override suspend fun build(param: Unit): Result<List<MovieListItemEntity>> {
        return repo.getFavoriteMovie()
    }
}