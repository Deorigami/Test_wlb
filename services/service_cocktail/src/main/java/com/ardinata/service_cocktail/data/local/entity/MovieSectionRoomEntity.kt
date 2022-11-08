package com.ardinata.service_cocktail.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_section")
data class MovieSectionRoomEntity(
    val sectionName : String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "sectionId") val id : Long = 0
)
