package com.ardinata.service_movie_db.domain.entity

import com.ardinata.service_movie_db.domain.resource.MovieDBSection

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
