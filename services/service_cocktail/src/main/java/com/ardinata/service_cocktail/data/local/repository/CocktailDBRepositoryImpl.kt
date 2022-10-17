package com.ardinata.service_cocktail.data.local.repository

import com.ardinata.service_cocktail.data.local.dao.CocktailDao
import com.ardinata.service_cocktail.data.local.entity.CocktailAndIngredientRelationEntity
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity
import com.ardinata.service_cocktail.domain.repository.CocktailDBRepository
import javax.inject.Inject
import com.ardinata.test.wlb.core.model.Result
import com.ardinata.service_cocktail.data.local.entity.CocktailDrinkItemDBEntity
import com.ardinata.service_cocktail.data.local.mapper.CocktailWithIngredientListMapper
import com.ardinata.service_cocktail.domain.mapper.CocktailItemDrinkEntityMapper


class CocktailDBRepositoryImpl @Inject constructor(
    private val dao : CocktailDao,
    private val cocktailItemDrinkEntityMapper: CocktailItemDrinkEntityMapper,
    private val cocktailWithIngredientListMapper: CocktailWithIngredientListMapper
) : CocktailDBRepository {
    override suspend fun insertFavDrink(param: CocktailDrinkItemEntity): Result<Long> {
        val cocktailId = dao.insertCocktailItem(cocktailItemDrinkEntityMapper.invoke(param))

        param.ingredientList.forEach {
            val ingredientId = dao.insertIngredient(CocktailDrinkItemDBEntity.IngredientsItemDBData(
                it.ingredient,
                it.measure,
            ))
            dao.insertCocktailIngredientMap(CocktailAndIngredientRelationEntity(cocktailId, ingredientId))
        }
        return Result(cocktailId)
    }

    override suspend fun getFavDrinks(): Result<List<CocktailDrinkItemEntity>> {
        return Result(cocktailWithIngredientListMapper.invoke(dao.getCocktailItemWithIngredients()))
    }

    override suspend fun deleteFavDrink(param: Long): Result<Int> {
        val result = dao.deleteFavoriteDrink(param)
        dao.deleteRelation(param)
        dao.deleteIngredrients(param)
        return Result(result)
    }
}