package com.ardinata.service_cocktail.domain.usecase.service

import com.ardinata.service_cocktail.domain.entity.TVListEntity
import com.ardinata.service_cocktail.domain.repository.MovieDBServiceRepository
import com.ardinata.test.wlb.core.base.BaseUseCase
import com.ardinata.test.wlb.core.model.Result
import javax.inject.Inject

class GetPopularTVUseCase @Inject constructor(
    private val repo: MovieDBServiceRepository
) : BaseUseCase<String, TVListEntity>(){
    override val default: TVListEntity
        get() = TVListEntity.DEFAULT

    override suspend fun build(param: String): Result<TVListEntity> {
        return repo.getPopularTV(param)
    }
}