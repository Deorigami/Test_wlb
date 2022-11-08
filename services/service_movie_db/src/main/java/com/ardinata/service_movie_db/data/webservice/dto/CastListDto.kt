package com.ardinata.service_movie_db.data.webservice.dto

import com.google.gson.annotations.SerializedName

data class CastListDto(
    @SerializedName("id") val id: Long,
    @SerializedName("cast") val cast: List<CastItemDto>
)
