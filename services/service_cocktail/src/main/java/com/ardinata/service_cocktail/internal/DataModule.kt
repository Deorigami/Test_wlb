package com.ardinata.service_cocktail.internal

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.ardinata.service_cocktail.data.local.CocktailDB
import com.ardinata.service_cocktail.data.local.dao.CocktailDao
import com.ardinata.service_cocktail.data.webservice.service.CocktailService
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
    fun profideService(retrofit: Retrofit) : CocktailService = retrofit.create(CocktailService::class.java)

    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context) : CocktailDB = Room.databaseBuilder(
        context,
        CocktailDB::class.java,
        "CocktailDB"
    ).setQueryCallback({ sqlQuery, _ ->
            Log.d("ANGGATAG", sqlQuery)
    }, Executors.newSingleThreadExecutor()).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesCocktailDao(db: CocktailDB) : CocktailDao = db.getDao()
}