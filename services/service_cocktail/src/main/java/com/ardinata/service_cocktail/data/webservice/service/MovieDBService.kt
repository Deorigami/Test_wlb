package com.ardinata.service_cocktail.data.webservice.service

import com.ardinata.service_cocktail.data.webservice.dto.MovieListDto
import com.ardinata.service_cocktail.data.webservice.dto.TVListDto
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
    ) : TVListDto

    @GET("tv/popular")
    suspend fun getPopularTV(
        @Query("page") page: String,
        @Query("language") lang:String = "en-US"
    ) : TVListDto

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTV(
        @Query("page") page: String,
        @Query("language") lang:String = "en-US"
    ) : TVListDto

    @GET("tv/airing_today")
    suspend fun getAiringTodayTV(
        @Query("page") page: String,
        @Query("language") lang:String = "en-US"
    ) : TVListDto


}