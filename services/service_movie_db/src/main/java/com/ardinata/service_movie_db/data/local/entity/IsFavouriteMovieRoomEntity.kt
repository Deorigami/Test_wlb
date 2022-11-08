package com.ardinata.service_movie_db.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movie_mapping")
data class IsFavouriteMovieRoomEntity(
    val isFav : Boolean,
    @PrimaryKey(autoGenerate = false) val isFavId : Long
)
