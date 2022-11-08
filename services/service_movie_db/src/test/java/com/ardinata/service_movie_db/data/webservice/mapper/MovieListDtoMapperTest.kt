package com.ardinata.service_movie_db.data.webservice.mapper

import com.ardinata.service_movie_db.data.webservice.dto.MovieListDto
import com.ardinata.service_movie_db.domain.entity.MovieListEntity
import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import com.ardinata.service_movie_db.domain.resource.MovieDBSection
import com.ardinata.test.test_goplay.core.extension.tryDeserialize
import com.ardinata.test.test_goplay.core.model.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MovieListDtoMapperTest {
    private val rawJson = """
        {
          "dates": {
            "maximum": "2022-11-06",
            "minimum": "2022-09-19"
          },
          "page": 1,
          "results": [
            {
              "adult": false,
              "backdrop_path": "/y5Z0WesTjvn59jP6yo459eUsbli.jpg",
              "genre_ids": [
                27,
                53
              ],
              "id": 663712,
              "original_language": "en",
              "original_title": "Terrifier 2",
              "overview": "After being resurrected by a sinister entity, Art the Clown returns to Miles County where he must hunt down and destroy a teenage girl and her younger brother on Halloween night.  As the body count rises, the siblings fight to stay alive while uncovering the true nature of Art's evil intent.",
              "popularity": 6116.349,
              "poster_path": "/b6IRp6Pl2Fsq37r9jFhGoLtaqHm.jpg",
              "release_date": "2022-10-06",
              "title": "Terrifier 2",
              "video": false,
              "vote_average": 7.1,
              "vote_count": 466
            }
          ],
          "total_pages": 100,
          "total_results": 1991
        }
    """.trimIndent()
    val dto = tryDeserialize<MovieListDto>(rawJson)
    val mapper = MovieListDtoMapper(MovieListItemDtoMapper())

    @Test
    fun invokeSuccess(){
        val actualResult = mapper.invoke(dto!!, MovieDBSection.NOW_PLAYING_MOVIE)
        val expected = Result(MovieListEntity(
            1,
            results = listOf(MovieListItemEntity(
                false,
                "/y5Z0WesTjvn59jP6yo459eUsbli.jpg",
                listOf(27, 53),
                663712,
                "en",
                "Terrifier 2",
                "After being resurrected by a sinister entity, Art the Clown returns to Miles County where he must hunt down and destroy a teenage girl and her younger brother on Halloween night.  As the body count rises, the siblings fight to stay alive while uncovering the true nature of Art's evil intent.",
                6116.349,
                "/b6IRp6Pl2Fsq37r9jFhGoLtaqHm.jpg",
                "2022-10-06",
                "Terrifier 2",
                false,
                7.1,
                466
            )),
            totalPages = 100,
            totalResults = 1991,
            section = MovieDBSection.NOW_PLAYING_MOVIE
        ))
        assertEquals(expected.data, actualResult.data)
    }
}