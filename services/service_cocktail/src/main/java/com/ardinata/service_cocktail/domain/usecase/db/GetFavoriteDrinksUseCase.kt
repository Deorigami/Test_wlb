package com.ardinata.service_cocktail.domain.usecase.db

import com.ardinata.test.wlb.core.base.BaseUseCase
import com.ardinata.test.wlb.core.model.Result
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity
import com.ardinata.service_cocktail.domain.repository.CocktailDBRepository
import javax.inject.Inject

class GetFavoriteDrinksUseCase @Inject constructor(
    private val dbRepo: CocktailDBRepository
) : BaseUseCase<Unit, List<CocktailDrinkItemEntity>>(){
    override val default: List<CocktailDrinkItemEntity>
        get() = emptyList()

    override suspend fun build(param: Unit): Result<List<CocktailDrinkItemEntity>> {
        return dbRepo.getFavDrinks()
    }
}