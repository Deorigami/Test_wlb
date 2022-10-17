package com.ardinata.service_cocktail.domain.entity

import com.ardinata.service_cocktail.domain.resource.FilterType

data class FilterEntity(
    val index: Long,
    val filterType : FilterType,
    val filterList : List<String>
) {
    companion object {
        val DEFAULT = FilterEntity(
            0,FilterType.Category, emptyList()
        )
    }
}
