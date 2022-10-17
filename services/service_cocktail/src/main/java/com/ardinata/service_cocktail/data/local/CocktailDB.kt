package com.ardinata.service_cocktail.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ardinata.service_cocktail.data.local.dao.CocktailDao
import com.ardinata.service_cocktail.data.local.entity.CocktailDrinkItemDBEntity
import com.ardinata.service_cocktail.data.local.entity.CocktailAndIngredientRelationEntity

@Database(
    entities = [
        CocktailAndIngredientRelationEntity::class,
        CocktailDrinkItemDBEntity::class,
        CocktailDrinkItemDBEntity.IngredientsItemDBData::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CocktailDB : RoomDatabase() {
    abstract fun getDao() : CocktailDao
}