package com.ardinata.service_cocktail.data.webservice.mapper

import com.ardinata.test.wlb.core.extension.capitalizeWords
import com.ardinata.test.wlb.core.extension.tryDeserialize
import com.ardinata.test.wlb.core.model.Result
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity
import com.google.gson.JsonElement
import javax.inject.Inject

class CocktailDrinkListDtoMapper @Inject constructor(
    private val cocktailIngredientsJsonMapper: CocktailIngredientsJsonMapper
) {
    operator fun invoke(from: JsonElement): Result<List<CocktailDrinkItemEntity>> {
        val resultJson = from.asJsonObject["drinks"].asJsonArray
        val result = mutableListOf<CocktailDrinkItemEntity>()
        resultJson.forEach { jsonElement ->
            val ingredientsList = cocktailIngredientsJsonMapper.invoke(jsonElement)
            val cocktailItem = tryDeserialize<CocktailDrinkItemEntity>(jsonElement.toString())
            cocktailItem?.let {
                result.add(it.copy(
                    ingredientList = ingredientsList,
                    glass = it.glass.capitalizeWords(),
                    category = it.category.capitalizeWords(),
                    alcoholic = it.alcoholic.capitalizeWords()
                ))
            }
        }
        return Result(result)
    }
}