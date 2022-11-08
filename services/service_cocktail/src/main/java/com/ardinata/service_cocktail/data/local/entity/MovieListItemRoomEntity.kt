package com.ardinata.service_cocktail.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_item")
data class MovieListItemRoomEntity(
    val adult: Boolean,
    val backdropPath: String,
    @ColumnInfo(name = "movieId") @PrimaryKey(autoGenerate = false) val id: Int,
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
    val page: Long
)
