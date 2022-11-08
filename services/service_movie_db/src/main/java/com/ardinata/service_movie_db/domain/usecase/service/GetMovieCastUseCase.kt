package com.ardinata.service_movie_db.domain.usecase.service

import com.ardinata.service_movie_db.domain.entity.CastListEntity
import com.ardinata.service_movie_db.domain.repository.MovieDBServiceRepository
import com.ardinata.test.test_goplay.core.base.BaseUseCase
import com.ardinata.test.test_goplay.core.model.Result
import javax.inject.Inject

class GetMovieCastUseCase @Inject constructor(
    private val repo: MovieDBServiceRepository
) : BaseUseCase<String, CastListEntity>(){
    override val default: CastListEntity
        get() = CastListEntity.DEFAULT

    override suspend fun build(param: String): Result<CastListEntity> {
        return repo.getMovieCast(param)
    }
}