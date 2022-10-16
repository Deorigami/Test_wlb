package com.eyedea.service_cocktail.domain.repository

import com.ardinata.test.wlb.core.model.Result
import com.eyedea.service_cocktail.data.local.model.CocktailWithIngredientList
import com.eyedea.service_cocktail.domain.entity.CocktailDrinkItemEntity

interface CocktailDBRepository {
    suspend fun insertFavDrink(param: CocktailDrinkItemEntity) : Result<Long>
    suspend fun getFavDrinks() : Result<List<CocktailDrinkItemEntity>>
    suspend fun deleteFavDrink(param: Long) : Result<Int>
}