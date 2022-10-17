package com.ardinata.service_cocktail.domain.usecase.db

import com.ardinata.test.wlb.core.base.BaseUseCase
import com.ardinata.test.wlb.core.model.Result
import com.ardinata.service_cocktail.domain.repository.CocktailDBRepository
import javax.inject.Inject

class DeleteFavDrinkUseCase @Inject constructor(
    private val repo : CocktailDBRepository
) : BaseUseCase<Long, Int>(){
    override val default: Int
        get() = 0

    override suspend fun build(param: Long): Result<Int> {
        return repo.deleteFavDrink(param)
    }
}