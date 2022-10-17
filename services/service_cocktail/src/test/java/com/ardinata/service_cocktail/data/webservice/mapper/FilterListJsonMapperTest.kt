package com.ardinata.service_cocktail.data.webservice.mapper

import com.ardinata.service_cocktail.domain.entity.FilterEntity
import com.ardinata.service_cocktail.domain.resource.FilterType
import com.ardinata.test.wlb.core.model.Result
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FilterListJsonMapperTest {
    private val rawJson = """
        {
          "drinks": [
            {
              "strAlcoholic": "Alcoholic"
            },
            {
              "strAlcoholic": "Non alcoholic"
            },
            {
              "strAlcoholic": "Optional alcohol"
            }
          ]
        }
    """.trimIndent()
    private val jsonElement: JsonElement = JsonParser.parseString(rawJson)
    private val mapper = FilterListJsonMapper()

    @Test
    fun invokeSuccess(){
        val actual = mapper.invoke(jsonElement)
        val expected = FilterEntity(
            index = 2,
            filterType = FilterType.Alcoholic,
            filterList = listOf("Alcoholic", "Non Alcoholic" ,"Optional Alcohol")
        )
        val expectedResult = Result(expected)
        assertEquals(expectedResult.data, actual.data)
    }
}