package com.ardinata.service_cocktail.domain.usecase

import com.ardinata.test.wlb.core.base.BaseUseCase
import com.ardinata.test.wlb.core.model.Result
import com.ardinata.service_cocktail.domain.entity.FilterEntity
import com.ardinata.service_cocktail.domain.repository.CocktailRepository
import javax.inject.Inject

class GetCategoriesFilterUseCase @Inject constructor(
    private val repo: CocktailRepository
) : BaseUseCase<Unit, FilterEntity>(){
    override val default: FilterEntity
        get() = FilterEntity.DEFAULT

    override suspend fun build(param: Unit): Result<FilterEntity> {
        return repo.getCategoriesFilter()
    }

}