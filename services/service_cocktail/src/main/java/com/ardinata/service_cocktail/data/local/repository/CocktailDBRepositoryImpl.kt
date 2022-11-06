package com.ardinata.service_cocktail.data.local.repository

import com.ardinata.service_cocktail.data.local.dao.CocktailDao
import javax.inject.Inject


class CocktailDBRepositoryImpl @Inject constructor(
    private val dao : CocktailDao,
) {

}