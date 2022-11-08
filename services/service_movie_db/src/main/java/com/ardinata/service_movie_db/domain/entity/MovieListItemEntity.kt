package com.ardinata.service_movie_db.domain.entity
import android.os.Parcelable
import com.ardinata.service_movie_db.domain.resource.MovieDBSection
import kotlinx.parcelize.Parcelize

@Parcelize
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
    val section: MovieDBSection = MovieDBSection.NOW_PLAYING_MOVIE,
    val isFavorite: Boolean = false
) : Parcelable {
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