package com.ardinata.service_cocktail.data.webservice.mapper

import com.ardinata.test.wlb.core.extension.capitalizeWords
import com.ardinata.test.wlb.core.model.Result
import com.ardinata.service_cocktail.domain.entity.FilterEntity
import com.ardinata.service_cocktail.domain.resource.FilterType
import com.google.gson.JsonElement
import javax.inject.Inject

class FilterListJsonMapper @Inject constructor() {
    operator fun invoke(from : JsonElement) : Result<FilterEntity> {
        val filterList = mutableListOf<String>()
        var index : Long = 0
        var filterType = FilterType.Category
        from.asJsonObject["drinks"].asJsonArray.forEach { drinksItem ->
            drinksItem.asJsonObject.keySet().forEach {
                filterList.add(drinksItem.asJsonObject[it].asString.capitalizeWords())
                when {
                    it.lowercase().contains("category") -> {
                        index = 0
                        filterType = FilterType.Category
                    }
                    it.lowercase().contains("glass") -> {
                        index = 1
                        filterType = FilterType.Glass
                    }
                    it.lowercase().contains("alcohol") -> {
                        index = 2
                        filterType = FilterType.Alcoholic
                    }
                    else -> Unit
                }
            }
        }
        val result = FilterEntity(
            index = index,
            filterType = filterType,
            filterList = filterList
        )
        return Result(result)
    }
}