package com.ardinata.service_movie_db.data.local.dao

import androidx.room.*
import com.ardinata.service_movie_db.data.local.entity.*

@Dao
interface MovieDBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(items : List<MovieListItemRoomEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(items : MovieListItemRoomEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieAndSectionRelation(movieAndSectionRelation : MovieAndSectionRelationEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieSection(movieSection : MovieSectionRoomEntity) : Long

    @Transaction
    @Query("SELECT * FROM movie_item")
    fun getMovieList() : List<MovieWithSectionRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteMovie(favMovie : IsFavouriteMovieRoomEntity) : Long

    @Query("SELECT * FROM favourite_movie_mapping WHERE isFav = 1")
    fun getFavouriteIDS() : List<IsFavouriteMovieRoomEntity>
}