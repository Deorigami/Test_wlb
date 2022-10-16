package com.eyedea.service_cocktail.domain.mapper

import androidx.room.ColumnInfo
import com.eyedea.service_cocktail.data.local.entity.CocktailDrinkItemDBEntity
import com.eyedea.service_cocktail.domain.entity.CocktailDrinkItemEntity
import javax.inject.Inject

class CocktailItemDrinkEntityMapper @Inject constructor() {
    operator fun invoke(from : CocktailDrinkItemEntity) : CocktailDrinkItemDBEntity {
        return CocktailDrinkItemDBEntity(
            from.dateModified,
            from.alcoholic,
            from.category,
            from.creativeCommonsConfirmed,
            from.drink,
            from.drinkAlternate,
            from.drinkThumb,
            from.glass,
            from.strIBA,
            from.imageAttribution,
            from.imageSource,
            from.strInstructions,
            from.tags,
            from.video,
            from.drinkId,
        )
    }
}