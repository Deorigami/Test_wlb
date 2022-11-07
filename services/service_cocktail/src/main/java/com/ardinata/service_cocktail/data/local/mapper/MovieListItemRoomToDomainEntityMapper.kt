package com.ardinata.service_cocktail.data.local.mapper

import com.ardinata.service_cocktail.data.local.entity.MovieListItemRoomEntity
import com.ardinata.service_cocktail.domain.entity.MovieListItemEntity
import com.ardinata.service_cocktail.domain.resource.MovieDBSection
import javax.inject.Inject

class MovieListItemRoomToDomainEntityMapper @Inject constructor() {
    operator fun invoke(from : MovieListItemRoomEntity, section: MovieDBSection) : MovieListItemEntity {
        return MovieListItemEntity(
            adult = from.adult,
            backdropPath = from.backdropPath,
            genreIds = emptyList(),
            id = from.id,
            originalLanguage = from.originalLanguage,
            originalTitle = from.originalTitle,
            overview = from.overview,
            popularity = from.popularity,
            posterPath = from.posterPath,
            releaseDate = from.releaseDate,
            title = from.title,
            video = from.video,
            voteAverage = from.voteAverage,
            voteCount = from.voteCount,
            page = from.page,
            section = section,
        )
    }
}