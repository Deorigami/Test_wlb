package com.eyedea.service_cocktail.domain.repository

import com.ardinata.test.wlb.core.model.Result
import com.eyedea.service_cocktail.domain.entity.CocktailDrinkItemEntity
import com.eyedea.service_cocktail.domain.entity.FilterEntity

interface CocktailRepository {
    suspend fun getCategoriesFilter() : Result<FilterEntity>
    suspend fun getGlassesFilter() : Result<FilterEntity>
    suspend fun getAlcoholicFilter() : Result<FilterEntity>
    suspend fun getCocktailList(param: String) : Result<List<CocktailDrinkItemEntity>>
    suspend fun searchCocktailByName(param: String) : Result<List<CocktailDrinkItemEntity>>
}