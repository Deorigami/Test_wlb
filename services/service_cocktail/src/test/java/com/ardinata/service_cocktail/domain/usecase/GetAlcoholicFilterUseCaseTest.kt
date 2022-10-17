package com.ardinata.service_cocktail.domain.usecase

import com.ardinata.test.wlb.core.model.Result
import com.ardinata.service_cocktail.domain.entity.FilterEntity
import com.ardinata.service_cocktail.domain.repository.CocktailRepository
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

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
internal class GetAlcoholicFilterUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var repo : CocktailRepository
    private lateinit var useCase: GetAlcoholicFilterUseCase


    @BeforeEach
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        useCase = GetAlcoholicFilterUseCase(repo)
    }

    @AfterEach
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun getDefault(){
        val actual = useCase.default
        val expected = FilterEntity.DEFAULT

        assertEquals(expected, actual)
    }

    @Test
    fun build() = runTest {
        //given
        val result = Result<FilterEntity>()
        given(repo.getAlcoholicFilter()).willReturn(result)

        // when
        val actualResult = useCase.build(Unit)

        // Then
        then(repo).should().getAlcoholicFilter()
        assertEquals(result, actualResult)
    }
}