package com.ardinata.service_cocktail.domain.entity
import com.ardinata.service_cocktail.domain.resource.MovieDBSection


data class MovieListEntity(
    val page: Int,
    val totalPages: Int,
    val results: List<MovieListItemEntity>,
    val totalResults: Int,
    val section: MovieDBSection
) {
    companion object {
        val DEFAULT = MovieListEntity(
            page = 0,
            totalPages = 0,
            results = emptyList(),
            totalResults = 0,
            section = MovieDBSection.NOW_PLAYING_MOVIE
        )
    }
}