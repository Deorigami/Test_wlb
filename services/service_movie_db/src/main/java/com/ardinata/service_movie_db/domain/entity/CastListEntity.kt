package com.ardinata.service_movie_db.domain.entity

data class CastListEntity(
    val id : Long,
    val cast : List<MovieCastItemEntity>
) {
    companion object{
        val DEFAULT = CastListEntity(
            0, emptyList()
        )
    }
}