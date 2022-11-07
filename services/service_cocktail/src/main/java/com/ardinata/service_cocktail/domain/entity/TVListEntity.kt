package com.ardinata.service_cocktail.domain.entity

import com.ardinata.service_cocktail.data.webservice.dto.MovieListItemDto
import com.ardinata.service_cocktail.domain.resource.MovieDBSection
import com.google.gson.annotations.SerializedName

data class TVListEntity(
    val page: Int,
    val totalPages: Int,
    val results: List<TVListItemEntity>,
    val totalResults: Int,
    val section: MovieDBSection
) {
    companion object {
        val DEFAULT = TVListEntity(
            0,0, emptyList(),0,MovieDBSection.POPULAR_TV
        )
    }
}
