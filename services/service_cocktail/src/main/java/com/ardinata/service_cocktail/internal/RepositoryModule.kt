package com.ardinata.service_cocktail.internal

import com.ardinata.service_cocktail.data.local.repository.MovieDBRoomRepositoryImpl
import com.ardinata.service_cocktail.data.webservice.repository.MovieDBServiceRepositoryImpl
import com.ardinata.service_cocktail.domain.repository.MovieDBRoomRepository
import com.ardinata.service_cocktail.domain.repository.MovieDBServiceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds abstract fun bindsMovieDBServiceRepository(repo: MovieDBServiceRepositoryImpl) : MovieDBServiceRepository

    @Binds abstract fun bindsMovieDVRoomRepository(repo: MovieDBRoomRepositoryImpl) : MovieDBRoomRepository
}