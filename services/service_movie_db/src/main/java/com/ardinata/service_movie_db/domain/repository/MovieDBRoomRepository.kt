package com.ardinata.service_movie_db.domain.repository

import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import com.ardinata.test.test_goplay.core.model.Result

interface MovieDBRoomRepository {
    suspend fun insertMovieList(item: List<MovieListItemEntity>) : Result<List<Long>>
    suspend fun getMovieBySection() : Result<List<MovieListItemEntity>>
    suspend fun getFavoriteMovie() : Result<List<MovieListItemEntity>>
    suspend fun insertFavouriteMovie(param: MovieListItemEntity) : Result<Long>
}