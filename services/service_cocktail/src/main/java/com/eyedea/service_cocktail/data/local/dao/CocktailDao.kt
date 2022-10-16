package com.eyedea.service_cocktail.data.local.dao

import androidx.room.*
import com.eyedea.service_cocktail.data.local.entity.CocktailDrinkItemDBEntity
import com.eyedea.service_cocktail.data.local.entity.CocktailAndIngredientRelationEntity
import com.eyedea.service_cocktail.data.local.model.CocktailWithIngredientList
import dagger.multibindings.LongKey

@Dao
interface CocktailDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCocktailItem(cocktailDrinkItemEntity: CocktailDrinkItemDBEntity) : Long
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIngredient(ingredientsItemData: CocktailDrinkItemDBEntity.IngredientsItemDBData) : Long
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCocktailIngredientMap(cocktailIngredientsMappingTable: CocktailAndIngredientRelationEntity) : Long

    @Transaction
    @Query("SELECT * FROM cocktail_item")
    suspend fun getCocktailItemWithIngredients() : List<CocktailWithIngredientList>

    @Query("DELETE FROM cocktail_item WHERE drinkId = :id")
    suspend fun deleteFavoriteDrink(id: Long) : Int
    @Query("DELETE FROM cocktail_ingredients WHERE ingredientId = :id")
    suspend fun deleteIngredrients(id: Long) : Int
    @Query("DELETE FROM cocktail_ingredient_relation WHERE drinkIdRef = :id")
    suspend fun deleteRelation(id: Long)
}