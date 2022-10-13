package com.eyedea.service_cocktail.internal

import com.eyedea.service_cocktail.data.webservice.repository.CocktailRepositoryImpl
import com.eyedea.service_cocktail.domain.repository.CocktailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepository(repo: CocktailRepositoryImpl) : CocktailRepository
}