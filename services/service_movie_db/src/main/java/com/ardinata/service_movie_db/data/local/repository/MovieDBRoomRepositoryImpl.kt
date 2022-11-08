package com.ardinata.service_movie_db.data.local.repository

import com.ardinata.service_movie_db.data.local.dao.MovieDBDao
import com.ardinata.service_movie_db.data.local.entity.MovieAndSectionRelationEntity
import com.ardinata.service_movie_db.data.local.entity.MovieSectionRoomEntity
import com.ardinata.service_movie_db.data.local.mapper.MovieListItemDomainEntityToRoomMapper
import com.ardinata.service_movie_db.data.local.mapper.MovieListItemRoomToDomainEntityMapper
import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import com.ardinata.service_movie_db.domain.repository.MovieDBRoomRepository
import com.ardinata.service_movie_db.domain.resource.MovieDBSection
import com.ardinata.test.test_goplay.core.model.Result
import javax.inject.Inject

class MovieDBRoomRepositoryImpl @Inject constructor(
    private val dao: MovieDBDao,
    private val movieListItemDBMapper: MovieListItemDomainEntityToRoomMapper,
    private val movieListItemRoomToDomainEntityMapper: MovieListItemRoomToDomainEntityMapper
) : MovieDBRoomRepository {
    override suspend fun insertMovieItem(item: List<MovieListItemEntity>): Result<List<Long>> {
        val insertReturn = dao.insertMovie(item.map { movieListItemDBMapper.invoke(it) })
        item.forEach {
            val sectionId = dao.insertMovieSection(MovieSectionRoomEntity(it.section.name))
            dao.insertMovieAndSectionRelation(MovieAndSectionRelationEntity(it.id.toLong(), sectionId))
        }
        return Result(insertReturn)
    }

    override suspend fun getMovieBySection(): Result<List<MovieListItemEntity>> {
        val data = dao.getMovieList()
        val movieList = mutableListOf<MovieListItemEntity>()

        data.forEach {
            it.movieSection.forEach { section ->
                val movieEntity = movieListItemRoomToDomainEntityMapper.invoke(it.movie, MovieDBSection.invoke(section.sectionName))
                movieList.add(movieEntity)
            }
        }
        return Result(movieList)
    }
}