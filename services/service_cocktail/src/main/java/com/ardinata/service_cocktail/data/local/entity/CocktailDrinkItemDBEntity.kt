package com.ardinata.service_cocktail.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktail_item")
data class CocktailDrinkItemDBEntity(
    @ColumnInfo(name = "dateModified") val dateModified: String? = "",
    @ColumnInfo(name = "strAlcoholic") val alcoholic: String,
    @ColumnInfo(name = "strCategory") val category: String,
    @ColumnInfo(name = "strCreativeCommonsConfirmed") val creativeCommonsConfirmed: String,
    @ColumnInfo(name = "strDrink") val drink: String,
    @ColumnInfo(name = "strDrinkAlternate") val drinkAlternate: String? = "",
    @ColumnInfo(name = "strDrinkThumb") val drinkThumb: String,
    @ColumnInfo(name = "strGlass") val glass: String,
    @ColumnInfo(name = "strIBA") val strIBA: String? = "",
    @ColumnInfo(name = "strImageAttribution") val imageAttribution: String? = "",
    @ColumnInfo(name = "strImageSource") val imageSource: String? = "",
    @ColumnInfo(name = "strInstructions") val strInstructions: String,
    @ColumnInfo(name = "strTags") val tags: String? = "",
    @ColumnInfo(name = "strVideo") val video: String? = "",
    @ColumnInfo(name = "drinkId") @PrimaryKey(autoGenerate = false) val drinkId: Long,
) {
    @Entity(tableName = "cocktail_ingredients")
    data class IngredientsItemDBData(
        @ColumnInfo(name = "ingredient") val ingredient: String,
        @ColumnInfo(name = "measure") val measure: String,
        @ColumnInfo(name = "image") val image: String,
        @ColumnInfo(name = "ingredientId") @PrimaryKey(autoGenerate = true) val id: Long = 0,
    )
}
