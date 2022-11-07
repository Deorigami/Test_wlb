package com.ardinata.service_cocktail.data.webservice.repository

import com.ardinata.service_cocktail.data.webservice.mapper.MovieListDtoMapper
import com.ardinata.service_cocktail.data.webservice.mapper.TVListDtoMapper
import com.ardinata.service_cocktail.data.webservice.service.MovieDBService
import com.ardinata.service_cocktail.domain.entity.MovieListEntity
import com.ardinata.service_cocktail.domain.entity.TVListEntity
import com.ardinata.service_cocktail.domain.repository.MovieDBServiceRepository
import com.ardinata.service_cocktail.domain.resource.MovieDBSection
import com.ardinata.test.wlb.core.model.Result
import javax.inject.Inject

class MovieDBServiceRepositoryImpl @Inject constructor(
    private val api: MovieDBService,
    private val movieListDtoMapper: MovieListDtoMapper,
    private val tvListDtoMapper: TVListDtoMapper
) : MovieDBServiceRepository {
    override suspend fun getTopRatedMovie(page: String): Result<MovieListEntity> {
        return movieListDtoMapper.invoke(api.getTopRatedMovie(page), MovieDBSection.TOP_RATED_MOVIE)
    }

    override suspend fun getUpcomingMovie(page: String): Result<MovieListEntity> {
        return movieListDtoMapper.invoke(api.getUpcomingMovie(page), MovieDBSection.UPCOMING_MOVIE)
    }

    override suspend fun getNowPlayingMovie(page: String): Result<MovieListEntity> {
        return movieListDtoMapper.invoke(api.getNowPlayingMovie(page), MovieDBSection.NOW_PLAYING_MOVIE)
    }

    override suspend fun getPopularMovie(page: String): Result<MovieListEntity> {
        return movieListDtoMapper.invoke(api.getPopularMovie(page), MovieDBSection.POPULAR_MOVIE)
    }

    override suspend fun getTopRatedTV(page: String): Result<TVListEntity> {
        return tvListDtoMapper.invoke(api.getTopRatedTV(page), MovieDBSection.TOP_RATED_TV)
    }

    override suspend fun getOnTheAirTV(page: String): Result<TVListEntity> {
        return tvListDtoMapper.invoke(api.getOnTheAirTV(page), MovieDBSection.ON_THE_AIR_TV)
    }

    override suspend fun getAiringTodayTV(page: String): Result<TVListEntity> {
        return tvListDtoMapper.invoke(api.getAiringTodayTV(page), MovieDBSection.AIRING_TODAY_TV)
    }

    override suspend fun getPopularTV(page: String): Result<TVListEntity> {
        return tvListDtoMapper.invoke(api.getPopularTV(page), MovieDBSection.POPULAR_TV)
    }
}