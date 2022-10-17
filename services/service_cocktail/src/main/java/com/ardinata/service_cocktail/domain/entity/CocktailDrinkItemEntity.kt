package com.ardinata.service_cocktail.domain.entity

import android.os.Parcelable
import com.ardinata.service_cocktail.data.local.entity.CocktailDrinkItemDBEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CocktailDrinkItemEntity(
    @SerializedName("dateModified") val dateModified: String? = "",
    @SerializedName("strAlcoholic") val alcoholic: String,
    @SerializedName("strCategory") val category: String,
    @SerializedName("strCreativeCommonsConfirmed") val creativeCommonsConfirmed: String,
    @SerializedName("strDrink") val drink: String,
    @SerializedName("strDrinkAlternate") val drinkAlternate: String? = "",
    @SerializedName("strDrinkThumb") val drinkThumb: String,
    @SerializedName("strGlass") val glass: String,
    @SerializedName("strIBA") val strIBA: String? = "",
    @SerializedName("strImageAttribution") val imageAttribution: String? = "",
    @SerializedName("strImageSource") val imageSource: String? = "",
    @SerializedName("strInstructions") val strInstructions: String,
    @SerializedName("strTags") val tags: String? = "",
    @SerializedName("strVideo") val video: String? = "",
    val ingredientList : List<IngredientsItemData> = listOf(),
    @SerializedName("idDrink") val drinkId: Long,
    ) : Parcelable {
    @Parcelize
    data class IngredientsItemData(
        val ingredient: String,
        val measure: String,
        val image: String,
        val id: Long? = null
    ) : Parcelable

    companion object {
        fun CocktailDrinkItemEntity.toCocktailItemDBEntity() = CocktailDrinkItemDBEntity(
            dateModified = dateModified,
            alcoholic = alcoholic,
            category = category,
            creativeCommonsConfirmed = creativeCommonsConfirmed,
            drink = drink,
            drinkAlternate = drinkAlternate,
            drinkThumb = drinkThumb,
            glass = glass,
            strIBA = strIBA,
            imageAttribution = imageAttribution,
            imageSource = imageSource,
            strInstructions = strInstructions,
            tags = tags,
            video = video,
            drinkId = drinkId,
        )

        fun IngredientsItemData.toIngredientDataDB() = CocktailDrinkItemDBEntity.IngredientsItemDBData(
            ingredient, measure,  image,id ?: 0
        )
    }
}