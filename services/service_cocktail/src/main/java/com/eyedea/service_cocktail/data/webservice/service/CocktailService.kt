package com.eyedea.service_cocktail.data.webservice.service

import com.google.gson.JsonElement
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailService {
    // Filter List
    @GET("list.php?c=list")
    suspend fun getCategoriesFilter() : JsonElement
    @GET("list.php?g=list")
    suspend fun getGlassFilter() : JsonElement
    @GET("list.php?a=list")
    suspend fun getAlcoholicFilter() : JsonElement

    @GET("search.php")
    suspend fun getDrinkList(
        @Query("f") find: String
    ) : JsonElement
}