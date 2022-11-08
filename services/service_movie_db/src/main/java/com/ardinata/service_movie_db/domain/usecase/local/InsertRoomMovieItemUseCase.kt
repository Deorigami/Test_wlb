package com.ardinata.service_movie_db.domain.usecase.local

import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import com.ardinata.service_movie_db.domain.repository.MovieDBRoomRepository
import com.ardinata.test.test_goplay.core.base.BaseUseCase
import com.ardinata.test.test_goplay.core.model.Result
import javax.inject.Inject

class InsertRoomMovieItemUseCase @Inject constructor(
    private val roomRepo: MovieDBRoomRepository
) : BaseUseCase<List<MovieListItemEntity>, List<Long>>(){

    override suspend fun build(param: List<MovieListItemEntity>): Result<List<Long>> {
        return roomRepo.insertMovieItem(param)
    }

    override val default: List<Long>
        get() = emptyList()

}