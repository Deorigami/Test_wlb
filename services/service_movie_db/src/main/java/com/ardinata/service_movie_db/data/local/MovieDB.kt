package com.ardinata.service_movie_db.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ardinata.service_movie_db.data.local.dao.MovieDBDao
import com.ardinata.service_movie_db.data.local.entity.MovieAndSectionRelationEntity
import com.ardinata.service_movie_db.data.local.entity.MovieListItemRoomEntity
import com.ardinata.service_movie_db.data.local.entity.MovieSectionRoomEntity

@Database(
    entities = [
        MovieListItemRoomEntity::class,
        MovieSectionRoomEntity::class,
        MovieAndSectionRelationEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class MovieDB : RoomDatabase() {
    abstract fun getDao() : MovieDBDao
}