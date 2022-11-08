package com.ardinata.service_movie_db.data.webservice.mapper

import com.ardinata.service_movie_db.data.webservice.dto.MovieListItemDto
import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import javax.inject.Inject

class MovieListItemDtoMapper @Inject constructor() {
    operator fun invoke(from : List<MovieListItemDto>) : List<MovieListItemEntity> {
        return from.map { MovieListItemEntity(
            adult = it.adult,
            backdropPath = "https://image.tmdb.org/t/p/w500${it.backdropPath}",
            genreIds = it.genreIds,
            id = it.id,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle ?: "",
            overview = it.overview,
            popularity = it.popularity,
            posterPath = "https://image.tmdb.org/t/p/w200${it.posterPath}",
            releaseDate = it.releaseDate ?: "",
            title = it.title,
            video = it.video,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
        ) }
    }
}