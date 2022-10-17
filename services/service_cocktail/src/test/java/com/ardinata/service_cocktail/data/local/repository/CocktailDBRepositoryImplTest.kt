package com.ardinata.service_cocktail.data.local.repository

import com.ardinata.service_cocktail.data.local.dao.CocktailDao
import com.ardinata.service_cocktail.data.local.entity.CocktailAndIngredientRelationEntity
import com.ardinata.service_cocktail.data.local.mapper.CocktailWithIngredientListMapper
import com.ardinata.service_cocktail.data.local.model.CocktailWithIngredientList
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity.Companion.toCocktailItemDBEntity
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity.Companion.toIngredientDataDB
import com.ardinata.service_cocktail.domain.mapper.CocktailItemDrinkEntityMapper
import com.ardinata.service_cocktail.domain.repository.CocktailDBRepository
import com.ardinata.test.wlb.core.model.Result
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.then
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
@OptIn(ExperimentalCoroutinesApi::class)
internal class CocktailDBRepositoryImplTest {
    private val dispatcher = StandardTestDispatcher()
    private lateinit var repo : CocktailDBRepository
    @Mock lateinit var dao: CocktailDao
    private val drinkItemEntity = CocktailDrinkItemEntity(
        dateModified = "2017-01-02 20:11:13" ,
        alcoholic = "Alcoholic" ,
        category = "Ordinary Drink" ,
        creativeCommonsConfirmed = "No" ,
        drink = "Algonquin" ,
        drinkAlternate = "" ,
        drinkThumb = "https://www.thecocktaildb.com/images/media/drink/uwryxx1483387873.jpg" ,
        glass = "Cocktail glass" ,
        strIBA = "" ,
        imageAttribution = "" ,
        imageSource = "" ,
        strInstructions = "" ,
        tags = "" ,
        video = "" ,
        ingredientList  = listOf(
            CocktailDrinkItemEntity.IngredientsItemData(
                "Blended whiskey",
                "1 1/2 oz",
                0
        )) ,
        drinkId = 11020 ,
    )
    private val drinkItemDbEntity = drinkItemEntity.toCocktailItemDBEntity()
    private val drinkIngredient = drinkItemEntity.ingredientList[0].toIngredientDataDB()
    private val drinkIngredientRelationMap = CocktailAndIngredientRelationEntity(drinkItemDbEntity.drinkId, drinkIngredient.id ?: 0)

    @BeforeEach
    fun setup(){
        Dispatchers.setMain(dispatcher)
        repo = CocktailDBRepositoryImpl(
            dao,
            CocktailItemDrinkEntityMapper(),
            CocktailWithIngredientListMapper()
        )
    }

    @AfterEach
    fun teardown(){
        Dispatchers.resetMain()
        dispatcher.cancel()
    }

    @Test
    fun insertFavDrink() = runTest {
        // given
        val result = Result(drinkItemDbEntity.drinkId)
        given(dao.insertCocktailItem(drinkItemDbEntity)).willReturn(drinkItemDbEntity.drinkId)
        given(dao.insertIngredient(drinkIngredient)).willReturn(0)
        given(dao.insertCocktailIngredientMap(drinkIngredientRelationMap)).willReturn(0)

        // when
        val actualResult = repo.insertFavDrink(drinkItemEntity)

        then(dao).should().insertCocktailItem(drinkItemDbEntity)
        then(dao).should().insertIngredient(drinkIngredient)
        then(dao).should().insertCocktailIngredientMap(drinkIngredientRelationMap)

        assertEquals(result.data, actualResult.data)
    }

    @Test
    fun getFavDrinkList() = runTest {
        val cocktailWithIngredientsEntity = CocktailWithIngredientList(
            drinkItemDbEntity.copy(),
            listOf(drinkIngredient)
        )
        // given
        val result = Result(listOf(drinkItemEntity))
        given(dao.getCocktailItemWithIngredients()).willReturn(listOf(cocktailWithIngredientsEntity))

        // when
        val actualResult = repo.getFavDrinks()

        then(dao).should().getCocktailItemWithIngredients()

        assertEquals(result.data, actualResult.data)
    }
}