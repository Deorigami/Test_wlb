package com.ardinata.feature_dashboard.landing.mapper

import com.ardinata.test.wlb.organism.CardItemView
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity

class CocktailDrinkEntityMapper {
    operator fun invoke(from : List<CocktailDrinkItemEntity>) : MutableList<CardItemView.Data> {
        return from.map { CardItemView.Data(
            imagePoster = "${it.drinkThumb}/preview",
            title = it.drink,
            category = it.category,
            glass = it.glass,
            alcohol = it.alcoholic,
        ) }.toMutableList()
    }
}