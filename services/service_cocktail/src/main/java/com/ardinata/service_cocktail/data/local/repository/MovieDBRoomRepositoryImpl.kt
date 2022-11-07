package com.ardinata.service_cocktail.data.local.repository

import android.util.Log
import com.ardinata.service_cocktail.data.local.dao.MovieDBDao
import com.ardinata.service_cocktail.data.local.entity.MovieAndSectionRelationEntity
import com.ardinata.service_cocktail.data.local.entity.MovieSectionRoomEntity
import com.ardinata.service_cocktail.data.local.mapper.MovieListItemDomainEntityToRoomMapper
import com.ardinata.service_cocktail.data.local.mapper.MovieListItemRoomToDomainEntityMapper
import com.ardinata.service_cocktail.domain.entity.MovieListItemEntity
import com.ardinata.service_cocktail.domain.repository.MovieDBRoomRepository
import com.ardinata.service_cocktail.domain.resource.MovieDBSection
import com.ardinata.test.wlb.core.model.Result
import javax.inject.Inject

class MovieDBRoomRepositoryImpl @Inject constructor(
    private val dao: MovieDBDao,
    private val movieListItemDBMapper: MovieListItemDomainEntityToRoomMapper,
    private val movieListItemRoomToDomainEntityMapper: MovieListItemRoomToDomainEntityMapper
) : MovieDBRoomRepository {
    override suspend fun insertMovieItem(item: List<MovieListItemEntity>): Result<Long> {
        item.forEach {
            dao.insertMovie(movieListItemDBMapper.invoke(it))
            val sectionId = dao.insertMovieSection(MovieSectionRoomEntity(it.section.name))
            dao.insertMovieandSectionRelation(MovieAndSectionRelationEntity(it.id.toLong(), sectionId))
        }
        return Result(1)
    }

    override suspend fun getMovieBySection(
        section: MovieDBSection,
    ): Result<List<MovieListItemEntity>> {
        val data = dao.getMovieList()
        val movieList = mutableListOf<MovieListItemEntity>()

        data.forEach {
            val movieEntity = movieListItemRoomToDomainEntityMapper.invoke(it.movie, MovieDBSection.invoke(it.movieSection.firstOrNull()?.sectionName ?: ""))
            movieList.add(movieEntity)
        }
        return Result(movieList.filter { it.section == section })
    }
}