package com.ardinata.service_movie_db.domain.usecase.service

import com.ardinata.service_movie_db.domain.entity.TVListEntity
import com.ardinata.service_movie_db.domain.repository.MovieDBServiceRepository
import com.ardinata.test.test_goplay.core.base.BaseUseCase
import com.ardinata.test.test_goplay.core.model.Result
import javax.inject.Inject

class GetAiringTodayTVUseCase @Inject constructor(
    private val repo: MovieDBServiceRepository
) : BaseUseCase<String, TVListEntity>(){
    override val default: TVListEntity
        get() = TVListEntity.DEFAULT

    override suspend fun build(param: String): Result<TVListEntity> {
        return repo.getAiringTodayTV(param)
    }
}