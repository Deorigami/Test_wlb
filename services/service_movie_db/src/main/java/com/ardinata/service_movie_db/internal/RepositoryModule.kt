package com.ardinata.service_movie_db.internal

import com.ardinata.service_movie_db.data.local.repository.MovieDBRoomRepositoryImpl
import com.ardinata.service_movie_db.data.webservice.repository.MovieDBServiceRepositoryImpl
import com.ardinata.service_movie_db.domain.repository.MovieDBRoomRepository
import com.ardinata.service_movie_db.domain.repository.MovieDBServiceRepository
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