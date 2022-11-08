package com.ardinata.service_movie_db.data.webservice.mapper

import com.ardinata.service_movie_db.data.webservice.dto.TVListItemDto
import com.ardinata.service_movie_db.domain.entity.TVListItemEntity
import javax.inject.Inject

class TVListItemDtoMaper @Inject constructor() {
    operator fun invoke(from :List<TVListItemDto>) :List<TVListItemEntity> {
        return from.map { TVListItemEntity(
            backdropPath = it.backdropPath ?: "",
            firstAirDate = it.firstAirDate ?: "",
            genreIds = it.genreIds,
            id = it.id,
            name = it.name,
            originCountry = it.originCountry,
            originalLanguage = it.originalLanguage,
            originalName = it.originalName,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath ?: "",
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
        ) }
    }
}