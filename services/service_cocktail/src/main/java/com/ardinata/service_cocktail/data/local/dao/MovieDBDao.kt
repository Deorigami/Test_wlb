package com.ardinata.service_cocktail.data.local.dao

import androidx.room.*
import com.ardinata.service_cocktail.data.local.entity.MovieAndSectionRelationEntity
import com.ardinata.service_cocktail.data.local.entity.MovieListItemRoomEntity
import com.ardinata.service_cocktail.data.local.entity.MovieSectionRoomEntity
import com.ardinata.service_cocktail.data.local.entity.MovieWithSectionRoomEntity

@Dao
interface MovieDBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(item : MovieListItemRoomEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieandSectionRelation(movieAndSectionRelation : MovieAndSectionRelationEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieSection(movieSection : MovieSectionRoomEntity) : Long

    @Transaction
    @Query("SELECT * FROM movie_item")
    fun getMovieList() : List<MovieWithSectionRoomEntity>

}