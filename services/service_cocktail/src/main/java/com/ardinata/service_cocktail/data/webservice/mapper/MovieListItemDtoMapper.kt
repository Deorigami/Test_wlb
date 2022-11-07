package com.ardinata.service_cocktail.data.webservice.mapper

import com.ardinata.service_cocktail.data.webservice.dto.MovieListItemDto
import com.ardinata.service_cocktail.domain.entity.MovieListEntity
import com.ardinata.service_cocktail.domain.entity.MovieListItemEntity
import javax.inject.Inject

class MovieListItemDtoMapper @Inject constructor() {
    operator fun invoke(from : List<MovieListItemDto>) : List<MovieListItemEntity> {
        return from.map { MovieListItemEntity(
            adult = it.adult,
            backdropPath = it.backdropPath ?: "",
            genreIds = it.genreIds,
            id = it.id,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle ?: "",
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath ?: "",
            releaseDate = it.releaseDate ?: "",
            title = it.title,
            video = it.video,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
        ) }
    }
}