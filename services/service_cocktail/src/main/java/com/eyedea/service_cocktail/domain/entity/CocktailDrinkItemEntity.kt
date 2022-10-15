package com.eyedea.service_cocktail.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CocktailDrinkItemEntity(
    @SerializedName("dateModified") val dateModified: String? = "",
    @SerializedName("idDrink") val idDrink: Long,
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
    val ingredientList : List<IngredientsItemData>
) : Parcelable {
    @Parcelize
    data class IngredientsItemData(
        val ingredient: String,
        val measure: String
    ) : Parcelable
}