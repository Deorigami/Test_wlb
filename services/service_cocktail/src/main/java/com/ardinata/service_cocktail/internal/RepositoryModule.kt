package com.ardinata.service_cocktail.internal

import com.ardinata.service_cocktail.data.local.repository.CocktailDBRepositoryImpl
import com.ardinata.service_cocktail.data.webservice.repository.CocktailRepositoryImpl
import com.ardinata.service_cocktail.domain.repository.CocktailDBRepository
import com.ardinata.service_cocktail.domain.repository.CocktailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepository(repo: CocktailRepositoryImpl) : CocktailRepository
    @Binds
    abstract fun bindsDBRepository(repo: CocktailDBRepositoryImpl) : CocktailDBRepository
}