package com.ardinata.service_cocktail.domain.usecase

import com.ardinata.test.wlb.core.base.BaseUseCase
import com.ardinata.test.wlb.core.model.Result
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity
import com.ardinata.service_cocktail.domain.repository.CocktailRepository
import javax.inject.Inject

class GetCocktailListUseCase @Inject constructor(
    private val repo: CocktailRepository
) : BaseUseCase<String, List<CocktailDrinkItemEntity>>(){
    override val default: List<CocktailDrinkItemEntity>
        get() = emptyList()

    override suspend fun build(param: String): Result<List<CocktailDrinkItemEntity>> {
        return repo.getCocktailList(param)
    }
}