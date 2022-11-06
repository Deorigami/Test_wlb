package com.ardinata.service_cocktail.domain.repository

import com.ardinata.service_cocktail.domain.entity.MovieListEntity
import com.ardinata.test.wlb.core.model.Result

interface MovieDBServiceRepository {
    suspend fun getTopRatedMovie(page: String) : Result<MovieListEntity>
    suspend fun getUpcomingMovie(page: String) : Result<MovieListEntity>
    suspend fun getNowPlayingMovie(page: String) : Result<MovieListEntity>
    suspend fun getPopularMovie(page: String) : Result<MovieListEntity>
    suspend fun getTopRatedTV(page: String) : Result<MovieListEntity>
    suspend fun getUpcomingTV(page: String) : Result<MovieListEntity>
    suspend fun getNowPlayingTV(page: String) : Result<MovieListEntity>
    suspend fun getPopularTV(page: String) : Result<MovieListEntity>
}