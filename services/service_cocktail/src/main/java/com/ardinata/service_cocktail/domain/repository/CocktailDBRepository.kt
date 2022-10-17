package com.ardinata.service_cocktail.domain.repository

import com.ardinata.test.wlb.core.model.Result
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity

interface CocktailDBRepository {
    suspend fun insertFavDrink(param: CocktailDrinkItemEntity) : Result<Long>
    suspend fun getFavDrinks() : Result<List<CocktailDrinkItemEntity>>
    suspend fun deleteFavDrink(param: Long) : Result<Int>
}