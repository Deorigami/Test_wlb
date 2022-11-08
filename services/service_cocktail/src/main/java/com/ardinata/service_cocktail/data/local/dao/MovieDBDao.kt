package com.ardinata.service_cocktail.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ardinata.service_cocktail.data.local.entity.MovieAndSectionRelationEntity
import com.ardinata.service_cocktail.data.local.entity.MovieListItemRoomEntity
import com.ardinata.service_cocktail.data.local.entity.MovieSectionRoomEntity

@Dao
interface MovieDBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(item : MovieListItemRoomEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieandSectionRelation(movieAndSectionRelation : MovieAndSectionRelationEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieSection(movieSection : MovieSectionRoomEntity) : Long


}