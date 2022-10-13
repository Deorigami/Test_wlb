package com.eyedea.service_cocktail.internal

import com.eyedea.service_cocktail.data.webservice.service.CocktailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun profideService(retrofit: Retrofit) : CocktailService = retrofit.create(CocktailService::class.java)
}