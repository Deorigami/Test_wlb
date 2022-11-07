package com.ardinata.service_cocktail.data.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithSectionRoomEntity(
    @Embedded
    val movie : MovieListItemRoomEntity,
    @Relation(
    entity = MovieSectionRoomEntity::class,
    parentColumn = "movieId",
    entityColumn = "sectionId",
    associateBy = Junction(
        value = MovieAndSectionRelationEntity::class,
        parentColumn = "movieIdRef",
        entityColumn = "sectionIdRef"
    ))
    val movieSection : List<MovieSectionRoomEntity>
)
