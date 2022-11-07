package com.ardinata.service_cocktail.data.webservice.mapper

import com.ardinata.service_cocktail.data.webservice.dto.TVListDto
import com.ardinata.service_cocktail.domain.entity.TVListEntity
import com.ardinata.service_cocktail.domain.resource.MovieDBSection
import com.ardinata.test.wlb.core.model.Result
import javax.inject.Inject

class TVListDtoMapper @Inject constructor(
    private val tvListItemDtoMaper: TVListItemDtoMaper
) {
    operator fun invoke(from : TVListDto, section: MovieDBSection) : Result<TVListEntity> {
        return Result(
            TVListEntity(
                page = from.page,
                totalPages = from.totalPages,
                results = tvListItemDtoMaper.invoke(from.results),
                totalResults = from.totalResults,
                section = section
            )
        )

    }
}