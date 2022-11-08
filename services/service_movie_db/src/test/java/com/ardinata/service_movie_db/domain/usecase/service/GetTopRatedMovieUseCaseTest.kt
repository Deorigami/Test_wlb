package com.ardinata.service_movie_db.domain.usecase.service

import com.ardinata.service_movie_db.domain.entity.MovieListEntity
import com.ardinata.service_movie_db.domain.repository.MovieDBServiceRepository
import com.ardinata.test.test_goplay.core.model.Result
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
internal class GetTopRatedMovieUseCaseTest{

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var repo: MovieDBServiceRepository
    private lateinit var useCase: GetTopRatedMovieUseCase

    @BeforeEach
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        useCase = GetTopRatedMovieUseCase(repo)
    }

    @AfterEach
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun getDefault(){
        val actual = useCase.default
        val expected = MovieListEntity.DEFAULT

        assertEquals(expected, actual)
    }

    @Test
    fun build() = runTest {
        //given
        val result = Result<MovieListEntity>()
        given(repo.getTopRatedMovie("1")).willReturn(result)

        // when
        val actualResult = useCase.build("1")

        // Then
        then(repo).should().getTopRatedMovie("1")
        assertEquals(result, actualResult)
    }

}