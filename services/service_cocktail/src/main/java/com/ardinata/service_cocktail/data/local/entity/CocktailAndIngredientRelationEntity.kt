package com.ardinata.service_cocktail.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "cocktail_ingredient_relation",
    primaryKeys = ["drinkIdRef", "ingredientIdRef"],
    foreignKeys = [
        ForeignKey(
            entity = CocktailDrinkItemDBEntity::class,
            parentColumns = ["drinkId"],
            childColumns = ["drinkIdRef"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = CocktailDrinkItemDBEntity.IngredientsItemDBData::class,
            parentColumns = ["ingredientId"],
            childColumns = ["ingredientIdRef"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class CocktailAndIngredientRelationEntity(
    val drinkIdRef: Long,
    @ColumnInfo(index = true) val ingredientIdRef: Long
)