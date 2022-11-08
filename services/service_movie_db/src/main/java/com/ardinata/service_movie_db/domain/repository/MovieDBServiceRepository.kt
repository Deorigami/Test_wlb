package com.ardinata.service_movie_db.domain.repository

import com.ardinata.service_movie_db.domain.entity.CastListEntity
import com.ardinata.service_movie_db.domain.entity.MovieListEntity
import com.ardinata.service_movie_db.domain.entity.SearchMovieRequestEntity
import com.ardinata.service_movie_db.domain.entity.TVListEntity
import com.ardinata.test.test_goplay.core.model.Result

interface MovieDBServiceRepository {
    suspend fun getTopRatedMovie(page: String) : Result<MovieListEntity>
    suspend fun getUpcomingMovie(page: String) : Result<MovieListEntity>
    suspend fun getNowPlayingMovie(page: String) : Result<MovieListEntity>
    suspend fun getPopularMovie(page: String) : Result<MovieListEntity>
    suspend fun getTopRatedTV(page: String) : Result<TVListEntity>
    suspend fun getOnTheAirTV(page: String) : Result<TVListEntity>
    suspend fun getAiringTodayTV(page: String) : Result<TVListEntity>
    suspend fun getPopularTV(page: String) : Result<TVListEntity>
    suspend fun searchTV(param : SearchMovieRequestEntity) : Result<MovieListEntity>
    suspend fun getMovieCast(param : String) : Result<CastListEntity>
    suspend fun getTVCast(param : String) : Result<CastListEntity>
}