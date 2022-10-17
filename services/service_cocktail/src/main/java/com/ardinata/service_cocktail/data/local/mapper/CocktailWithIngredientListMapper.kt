package com.ardinata.service_cocktail.data.local.mapper

import com.ardinata.service_cocktail.data.local.model.CocktailWithIngredientList
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity
import javax.inject.Inject

class CocktailWithIngredientListMapper @Inject constructor() {
    operator fun invoke(from : List<CocktailWithIngredientList>) : List<CocktailDrinkItemEntity> {
        return from.map { ingredientList ->
            CocktailDrinkItemEntity(
            dateModified = ingredientList.cocktailDrinkItemEntity.dateModified,
            alcoholic = ingredientList.cocktailDrinkItemEntity.alcoholic,
            category = ingredientList.cocktailDrinkItemEntity.category,
            creativeCommonsConfirmed = ingredientList.cocktailDrinkItemEntity.creativeCommonsConfirmed,
            drink = ingredientList.cocktailDrinkItemEntity.drink,
            drinkAlternate = ingredientList.cocktailDrinkItemEntity.drinkAlternate,
            drinkThumb = ingredientList.cocktailDrinkItemEntity.drinkThumb,
            glass = ingredientList.cocktailDrinkItemEntity.glass,
            strIBA = ingredientList.cocktailDrinkItemEntity.strIBA,
            imageAttribution = ingredientList.cocktailDrinkItemEntity.imageAttribution,
            imageSource = ingredientList.cocktailDrinkItemEntity.imageSource,
            strInstructions = ingredientList.cocktailDrinkItemEntity.strInstructions,
            tags = ingredientList.cocktailDrinkItemEntity.tags,
            video = ingredientList.cocktailDrinkItemEntity.video,
            ingredientList = ingredientList.ingredientList.map { CocktailDrinkItemEntity.IngredientsItemData(
                it.ingredient,
                it.measure,
                it.id
            ) },
            drinkId = ingredientList.cocktailDrinkItemEntity.drinkId,
        ) }
    }
}