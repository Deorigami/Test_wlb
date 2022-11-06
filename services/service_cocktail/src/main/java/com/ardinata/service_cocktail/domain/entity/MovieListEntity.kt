package com.ardinata.service_cocktail.domain.entity
import com.ardinata.service_cocktail.domain.resource.MOVIESECTION


data class MovieListEntity(
    val page: Int,
    val totalPages: Int,
    val results: List<MovieListItemEntity>,
    val totalResults: Int,
    val section: MOVIESECTION
) {
    companion object {
        val DEFAULT = MovieListEntity(
            page = 0,
            totalPages = 0,
            results = emptyList(),
            totalResults = 0,
            section = MOVIESECTION.NOW_PLAYING_MOVIE
        )
    }
}