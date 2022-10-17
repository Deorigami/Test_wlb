package com.ardinata.service_cocktail.data.webservice.mapper

import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity.IngredientsItemData
import com.google.gson.JsonElement
import javax.inject.Inject

class CocktailIngredientsJsonMapper @Inject constructor() {
    operator fun invoke(from: JsonElement): List<IngredientsItemData> {
        val jsonObj = from.asJsonObject
        val ingredientList = mutableListOf<IngredientsItemData>()
        jsonObj
            .keySet()
            .filter { it.lowercase().contains("ingredient") || it.lowercase().contains("measure") }
            .let { key ->
                val loop = key.size / 2
                key.forEachIndexed { index, s ->
                    val ingredient = try { jsonObj[s].asString } catch (e:Exception) { "" }
                    val measure = try { jsonObj[key[index + loop]].asString } catch (e: Exception) { "" }
                    if (index <= loop && ingredient.isNotEmpty() && measure.isNotEmpty()){
                        ingredientList.add(IngredientsItemData(ingredient, measure))
                    }
                }
            }
        return ingredientList
    }
}