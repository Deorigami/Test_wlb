package com.ardinata.service_movie_db.data.webservice.mapper

import com.ardinata.service_movie_db.data.webservice.dto.CastListDto
import com.ardinata.service_movie_db.domain.entity.MovieCastItemEntity
import com.ardinata.service_movie_db.domain.entity.CastListEntity
import com.ardinata.test.test_goplay.core.model.Result
import javax.inject.Inject

class MovieCastListDtoMapper @Inject constructor() {
    operator fun invoke(from: CastListDto): Result<CastListEntity> {
        return Result(
            CastListEntity(
                from.id,
                from.cast.map {
                    MovieCastItemEntity(
                        adult = it.adult,
                        castId = it.castId,
                        character = it.character,
                        creditId = it.creditId,
                        gender = it.gender,
                        id = it.id,
                        knownForDepartment = it.knownForDepartment,
                        name = it.name,
                        order = it.order,
                        originalName = it.originalName,
                        popularity = it.popularity,
                        profilePath = "https://image.tmdb.org/t/p/w200${it.profilePath}",
                    )
                }
            )
        )
    }
}