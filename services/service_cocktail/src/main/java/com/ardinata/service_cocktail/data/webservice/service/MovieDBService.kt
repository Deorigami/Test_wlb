package com.ardinata.service_cocktail.data.webservice.service

import com.ardinata.service_cocktail.data.webservice.dto.MovieListDto
import com.google.gson.JsonElement
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("page") page: String,
        @Query("language") lang:String = "en-US"
    ) : MovieListDto

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("page") page: String,
        @Query("language") lang:String = "en-US"
    ) : MovieListDto

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("page") page: String,
        @Query("language") lang:String = "en-US"
    ) : MovieListDto

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: String,
        @Query("language") lang:String = "en-US"
    ) : MovieListDto


    @GET("tv/top_rated")
    suspend fun getTopRatedTV(
        @Query("page") page: String,
        @Query("language") lang:String = "en-US"
    ) : MovieListDto

    @GET("tv/upcoming")
    suspend fun getUpcomingTV(
        @Query("page") page: String,
        @Query("language") lang:String = "en-US"
    ) : MovieListDto

    @GET("tv/now_playing")
    suspend fun getNowPlayingTV(
        @Query("page") page: String,
        @Query("language") lang:String = "en-US"
    ) : MovieListDto

    @GET("tv/popular")
    suspend fun getPopularTV(
        @Query("page") page: String,
        @Query("language") lang:String = "en-US"
    ) : MovieListDto


}