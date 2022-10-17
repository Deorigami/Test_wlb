package com.ardinata.service_cocktail.domain.usecase.db

import com.ardinata.test.wlb.core.base.BaseUseCase
import com.ardinata.test.wlb.core.model.Result
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity
import com.ardinata.service_cocktail.domain.repository.CocktailDBRepository
import javax.inject.Inject

class InsertFavDrinksUseCase @Inject constructor(
    private val dbRepo: CocktailDBRepository
) : BaseUseCase<CocktailDrinkItemEntity, Long>(){
    override val default: Long
        get() = 0

    override suspend fun build(param: CocktailDrinkItemEntity): Result<Long> {
        return dbRepo.insertFavDrink(param)
    }
}