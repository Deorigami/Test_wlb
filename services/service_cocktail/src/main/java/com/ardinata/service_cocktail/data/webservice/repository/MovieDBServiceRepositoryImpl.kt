package com.ardinata.service_cocktail.data.webservice.repository

import com.ardinata.service_cocktail.data.webservice.mapper.MovieListDtoMapper
import com.ardinata.service_cocktail.data.webservice.service.MovieDBService
import com.ardinata.service_cocktail.domain.entity.MovieListEntity
import com.ardinata.service_cocktail.domain.repository.MovieDBServiceRepository
import com.ardinata.service_cocktail.domain.resource.MOVIESECTION
import com.ardinata.test.wlb.core.model.Result
import javax.inject.Inject

class MovieDBServiceRepositoryImpl @Inject constructor(
    private val api: MovieDBService,
    private val movieListDtoMapper: MovieListDtoMapper
) : MovieDBServiceRepository {
    override suspend fun getTopRatedMovie(page: String): Result<MovieListEntity> {
        return movieListDtoMapper.invoke(api.getTopRatedMovie(page), MOVIESECTION.TOP_RATED_MOVIE)
    }

    override suspend fun getUpcomingMovie(page: String): Result<MovieListEntity> {
        return movieListDtoMapper.invoke(api.getUpcomingMovie(page), MOVIESECTION.UPCOMING_MOVIE)
    }

    override suspend fun getNowPlayingMovie(page: String): Result<MovieListEntity> {
        return movieListDtoMapper.invoke(api.getNowPlayingMovie(page), MOVIESECTION.NOW_PLAYING_MOVIE)
    }

    override suspend fun getPopularMovie(page: String): Result<MovieListEntity> {
        return movieListDtoMapper.invoke(api.getPopularMovie(page), MOVIESECTION.POPULAR_MOVIE)
    }

    override suspend fun getTopRatedTV(page: String): Result<MovieListEntity> {
        return movieListDtoMapper.invoke(api.getPopularTV(page), MOVIESECTION.TOP_RATED_TV)
    }

    override suspend fun getUpcomingTV(page: String): Result<MovieListEntity> {
        return movieListDtoMapper.invoke(api.getUpcomingTV(page), MOVIESECTION.UPCOMING_TV)
    }

    override suspend fun getNowPlayingTV(page: String): Result<MovieListEntity> {
        return movieListDtoMapper.invoke(api.getNowPlayingTV(page), MOVIESECTION.NOW_PLAYING_TV)
    }

    override suspend fun getPopularTV(page: String): Result<MovieListEntity> {
        return movieListDtoMapper.invoke(api.getPopularTV(page), MOVIESECTION.POPULAR_TV)
    }
}