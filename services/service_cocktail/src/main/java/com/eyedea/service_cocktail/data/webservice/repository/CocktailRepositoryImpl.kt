package com.eyedea.service_cocktail.data.webservice.repository

import com.ardinata.test.wlb.core.model.Result
import com.eyedea.service_cocktail.data.webservice.mapper.CocktailDrinkListDtoMapper
import com.eyedea.service_cocktail.data.webservice.mapper.FilterListJsonMapper
import com.eyedea.service_cocktail.data.webservice.service.CocktailService
import com.eyedea.service_cocktail.domain.entity.CocktailDrinkItemEntity
import com.eyedea.service_cocktail.domain.entity.FilterEntity
import com.eyedea.service_cocktail.domain.repository.CocktailRepository
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val api : CocktailService,
    private val filterListJsonMapper: FilterListJsonMapper,
    private val cocktailDrinkListDtoMapper: CocktailDrinkListDtoMapper
) : CocktailRepository {
    override suspend fun getCategoriesFilter(): Result<FilterEntity> {
        return filterListJsonMapper.invoke(api.getCategoriesFilter())
    }

    override suspend fun getGlassesFilter(): Result<FilterEntity> {
        return filterListJsonMapper.invoke(api.getGlassFilter())
    }

    override suspend fun getAlcoholicFilter(): Result<FilterEntity> {
        return filterListJsonMapper.invoke(api.getAlcoholicFilter())
    }

    override suspend fun getCocktailList(param: String): Result<List<CocktailDrinkItemEntity>> {
        return cocktailDrinkListDtoMapper.invoke(api.getDrinkList(param))
    }
}