package com.ardinata.service_cocktail.domain.resource

enum class MovieDBSection(
    val text: String,
    val id: Long
) {
    NOW_PLAYING_MOVIE("Now Playing", 1),
    POPULAR_MOVIE("Popular", 2),
    TOP_RATED_MOVIE("Top Rated", 3),
    UPCOMING_MOVIE("Upcoming", 4),

    AIRING_TODAY_TV("Airing Today", 5),
    TOP_RATED_TV("Top Rated", 6),
    ON_THE_AIR_TV("On The Air", 7),
    POPULAR_TV("Popular", 8);

    operator fun invoke(name: String) : MovieDBSection = values().firstOrNull { it.name == name } ?: NOW_PLAYING_MOVIE
}