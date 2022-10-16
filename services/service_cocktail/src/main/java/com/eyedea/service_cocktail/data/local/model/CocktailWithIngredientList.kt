package com.eyedea.service_cocktail.data.local.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.eyedea.service_cocktail.data.local.entity.CocktailDrinkItemDBEntity
import com.eyedea.service_cocktail.data.local.entity.CocktailAndIngredientRelationEntity

data class CocktailWithIngredientList(
    @Embedded
    val cocktailDrinkItemEntity: CocktailDrinkItemDBEntity,
    @Relation(
        entity = CocktailDrinkItemDBEntity.IngredientsItemDBData::class,
        parentColumn = "drinkId",
        entityColumn = "ingredientId",
        associateBy = Junction(
            value = CocktailAndIngredientRelationEntity::class,
            parentColumn = "drinkIdRef",
            entityColumn = "ingredientIdRef"
        )
    )
    val ingredientList : List<CocktailDrinkItemDBEntity.IngredientsItemDBData>
)