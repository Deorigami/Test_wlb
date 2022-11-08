package com.ardinata.service_cocktail.internal

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.ardinata.service_cocktail.data.local.MovieDB
import com.ardinata.service_cocktail.data.local.dao.MovieDBDao
import com.ardinata.service_cocktail.data.webservice.service.MovieDBService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun profideService(retrofit: Retrofit) : MovieDBService = retrofit.create(MovieDBService::class.java)

    @Provides
    @Singleton
    fun providesMovieDB(@ApplicationContext context: Context) : MovieDB = Room.databaseBuilder(
        context,
        MovieDB::class.java,
        "MovieDB"
    ).setQueryCallback({ sqlQuery, _ ->
        Log.d("ROOM DB", sqlQuery)
    }, Executors.newSingleThreadExecutor()).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesMovieDao(db: MovieDB) : MovieDBDao = db.getDao()

}