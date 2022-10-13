package com.eyedea.service_cocktail.domain.entity

data class FilterEntity(
    val index: Long,
    val filterTitle : String,
    val filterList : List<String>
) {
    companion object {
        val DEFAULT = FilterEntity(
            0,"", emptyList()
        )
    }
}
