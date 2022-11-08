package com.ardinata.service_cocktail.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "movie_section_relation",
    primaryKeys = ["movieIdRef", "sectionIdRef"],
    foreignKeys = [
        ForeignKey(
            entity = MovieListItemRoomEntity::class,
            parentColumns = ["movieId"],
            childColumns = ["movieIdRef"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = MovieSectionRoomEntity::class,
            parentColumns = ["sectionId"],
            childColumns = ["sectionIdRef"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class MovieAndSectionRelationEntity(
    val movieIdRef : Long,
    @ColumnInfo(index = true) val sectionIdRef : Long,
)
