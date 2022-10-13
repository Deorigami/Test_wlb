package com.eyedea.service_cocktail.data.webservice.mapper

import android.util.Log
import com.ardinata.test.wlb.core.model.Result
import com.eyedea.service_cocktail.domain.entity.FilterEntity
import com.google.gson.JsonElement
import javax.inject.Inject

class FilterListJsonMapper @Inject constructor() {
    operator fun invoke(from : JsonElement) : Result<FilterEntity> {
        val filterList = mutableListOf<String>()
        var index : Long = 0
        var title = ""
        from.asJsonObject["drinks"].asJsonArray.forEach { drinksItem ->
            drinksItem.asJsonObject.keySet().forEach {
                filterList.add(drinksItem.asJsonObject[it].toString())
                when {
                    it.lowercase().contains("category") -> {
                        index = 0
                        title = "Category"
                    }
                    it.lowercase().contains("glass") -> {
                        index = 1
                        title = "Glass"
                    }
                    it.lowercase().contains("alcohol") -> {
                        index = 2
                        title = "Alcoholic"
                    }
                    else -> Unit
                }
            }
        }
        val result = FilterEntity(
            index = index,
            filterTitle = title,
            filterList = filterList
        )
        return Result(result)
    }
}