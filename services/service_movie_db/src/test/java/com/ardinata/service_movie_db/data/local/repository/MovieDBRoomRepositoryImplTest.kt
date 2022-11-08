package com.ardinata.service_movie_db.data.local.repository

import com.ardinata.service_movie_db.data.local.dao.MovieDBDao
import com.ardinata.service_movie_db.data.local.entity.MovieAndSectionRelationEntity
import com.ardinata.service_movie_db.data.local.entity.MovieListItemRoomEntity
import com.ardinata.service_movie_db.data.local.entity.MovieSectionRoomEntity
import com.ardinata.service_movie_db.data.local.entity.MovieWithSectionRoomEntity
import com.ardinata.service_movie_db.data.local.mapper.MovieListItemDomainEntityToRoomMapper
import com.ardinata.service_movie_db.data.local.mapper.MovieListItemRoomToDomainEntityMapper
import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import com.ardinata.service_movie_db.domain.repository.MovieDBRoomRepository
import com.ardinata.service_movie_db.domain.resource.MovieDBSection
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
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
@OptIn(ExperimentalCoroutinesApi::class)
internal class MovieDBRoomRepositoryImplTest {
    private val dispatcher = StandardTestDispatcher()
    private lateinit var repo : MovieDBRoomRepository
    private val movieListItemDBMapper: MovieListItemDomainEntityToRoomMapper = MovieListItemDomainEntityToRoomMapper()
    private val movieListItemRoomToDomainEntityMapper: MovieListItemRoomToDomainEntityMapper = MovieListItemRoomToDomainEntityMapper()

    @Mock lateinit var dao: MovieDBDao
    private lateinit var movieItemEntity : MovieListItemEntity
    private lateinit var movieSection : MovieSectionRoomEntity
    private lateinit var movieAndSectionRelationEntity : MovieAndSectionRelationEntity

    @BeforeEach
    fun setup(){
        Dispatchers.setMain(dispatcher)
        repo = MovieDBRoomRepositoryImpl(
            dao,
            movieListItemDBMapper,
            movieListItemRoomToDomainEntityMapper
        )
        movieItemEntity = MovieListItemEntity(
            false,
            "/a6OX0pzbcPHstcNPK3FSJGq8Sxp.jpg",
            listOf(16,14,12),
            955644,
            "en",
            "Mia and Me: The Hero of Centopia",
            "Mia discovers her magic stone is part of an ancient prophecy and embarks on a thrilling journey to the farthest islands of Centopia to face a great evil, and shape her own destiny.",
            771.47,
            "/a3ZpDsvNCQUOD7ApAs9y3mFwVU4.jpg",
            "2022-05-26",
            "Mia and Me: The Hero of Centopia",
            false,
            7.2,
            27,
            1,
            MovieDBSection.NOW_PLAYING_MOVIE,
        )
        movieSection = MovieSectionRoomEntity(movieItemEntity.section.name)
        movieAndSectionRelationEntity = MovieAndSectionRelationEntity(movieItemEntity.id.toLong(), 0)
    }

    @AfterEach
    fun teardown(){
        Dispatchers.resetMain()
        dispatcher.cancel()
    }

    @Test
    fun insertMovie() = runTest {
        // given
        val result: Result<List<Long>> = Result(listOf(1655))
        val movieItemRoomEntity = movieListItemDBMapper.invoke(movieItemEntity)
        given(dao.insertMovie(listOf(movieItemRoomEntity))).willReturn(listOf(1655))
        given(dao.insertMovieSection(movieSection)).willReturn(movieSection.id)
        given(dao.insertMovieAndSectionRelation(movieAndSectionRelationEntity)).willReturn(1)

        // when
        val actualResult = repo.insertMovieItem(listOf(movieItemEntity))

        then(dao).should().insertMovie(listOf(movieItemRoomEntity))
        then(dao).should().insertMovieSection(movieSection)
        then(dao).should().insertMovieAndSectionRelation(movieAndSectionRelationEntity)

        assertEquals(result.data, actualResult.data)
    }

    @Test
    fun getFavDrinkList() = runTest {
        val movieWithSectionRoomEntity = MovieWithSectionRoomEntity(
            MovieListItemRoomEntity(
                false,
                "/lmSkzYoflCDYF124P3Hncds2Iah.jpg",
                1040504,
                "en",
                "Going All The Way - The Director's Edit",
                "Mark Pellington's adaptation of Dan Wakefield's seminal novel. An elegant and morally complex tale about two young high school alumni and Korean war veterans returning to their sheltered Indianapolis community, only to find they no longer fit in. As classmates, shy, artistic Sonny and charming, popular Gunner had nothing to do with one another, but now, in the stifling climate of Eisenhower America, where prejudice and paranoia rule the day, the two young men find in each other the strength to change their lives and futures. Each must choose between the suffocating, but familiar comforts offered to them by their mothers and their old flames and friends, or the exciting, but uncertain futures represented by a pair of enthralling new romantic prospects. Originally released in 1997, the newly re-edited and restored version completely upends the original cut, hews closer to the source novel.",
                28.898,
                "/smcjqTwbK0Iu9VbYcygbAj6A8Rz.jpg",
                "2022-11-07",
                "Going All The Way - The Director's Edit",
                false,
                0.0,
                0,
                3
            ),
            listOf(MovieSectionRoomEntity(MovieDBSection.NOW_PLAYING_MOVIE.name, 1))
        )
        val movieListItemEntity = movieListItemRoomToDomainEntityMapper.invoke(
            movieWithSectionRoomEntity.movie,
            MovieDBSection.NOW_PLAYING_MOVIE
        )
        // given
        val result = Result(listOf(movieListItemEntity))
        given(dao.getMovieList()).willReturn(listOf(movieWithSectionRoomEntity))

        // when
        val actualResult = repo.getMovieBySection()

        then(dao).should().getMovieList()

        assertEquals(result.data, actualResult.data)
    }
}