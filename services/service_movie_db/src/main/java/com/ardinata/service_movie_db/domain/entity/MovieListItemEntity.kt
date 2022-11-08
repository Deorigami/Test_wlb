package com.ardinata.service_movie_db.domain.entity
import com.ardinata.service_movie_db.domain.resource.MovieDBSection


data class MovieListItemEntity(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val page: Long = 1L,
    val section: MovieDBSection = MovieDBSection.NOW_PLAYING_MOVIE
) {
    companion object{
        val DEFAULT = MovieListItemEntity(
            false,
            "",
            emptyList(),
            0,
            "",
            "",
            "",
            0.0,
            "",
            "",
            "",
            false,
            0.0,
            0,
            1,
            MovieDBSection.NONE,
        )
    }
}