package com.ardinata.service_movie_db.data.webservice.service

import com.ardinata.service_movie_db.data.webservice.dto.CastListDto
import com.ardinata.service_movie_db.data.webservice.dto.MovieListDto
import com.ardinata.service_movie_db.data.webservice.dto.TVListDto
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query : String,
        @Query("page") page: String,
        @Query("language") lang:String = "en-US"
    ) : MovieListDto

    @GET("search/tv")
    suspend fun searchTV(
        @Query("query") query : String
    ) : TVListDto

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieID : String,
        @Query("language") lang:String = "en-US"
    ) : CastListDto

    @GET("tv/{series_id}/credits")
    suspend fun getTVCredits(
        @Path("series_id") movieID : String,
        @Query("language") lang:String = "en-US"
    ) : CastListDto
}