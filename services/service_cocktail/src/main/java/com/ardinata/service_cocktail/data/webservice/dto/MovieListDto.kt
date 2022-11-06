package com.ardinata.service_cocktail.data.webservice.dto
import com.google.gson.annotations.SerializedName



data class MovieListDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<MovieListItemDto>,
    @SerializedName("total_results")
    val totalResults: Int
)