package com.ardinata.service_cocktail.data.webservice.mapper

import com.ardinata.service_cocktail.data.webservice.dto.MovieListDto
import com.ardinata.service_cocktail.domain.entity.MovieListEntity
import com.ardinata.service_cocktail.domain.resource.MovieDBSection
import com.ardinata.test.wlb.core.model.Result
import javax.inject.Inject

class MovieListDtoMapper @Inject constructor(
    private val movieListItemDtoMapper: MovieListItemDtoMapper
) {
    operator fun invoke(from: MovieListDto, section: MovieDBSection): Result<MovieListEntity> {
        return Result(
            MovieListEntity(
                page = from.page,
                totalPages = from.totalPages,
                results = movieListItemDtoMapper.invoke(from.results),
                totalResults = from.totalResults,
                section = section
            )
        )
    }
}