package com.eyedea.service_cocktail.domain.usecase

import com.ardinata.test.wlb.core.base.BaseUseCase
import com.ardinata.test.wlb.core.model.Result
import com.eyedea.service_cocktail.domain.entity.FilterEntity
import com.eyedea.service_cocktail.domain.repository.CocktailRepository
import javax.inject.Inject

class GetGlassesFilterUseCase @Inject constructor(
    private val repo: CocktailRepository
) : BaseUseCase<Unit, FilterEntity>(){
    override val default: FilterEntity
        get() = FilterEntity.DEFAULT

    override suspend fun build(param: Unit): Result<FilterEntity> {
        return repo.getGlassesFilter()
    }

}