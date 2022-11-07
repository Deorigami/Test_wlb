package com.ardinata.service_cocktail.domain.resource

enum class MovieDBSection(
    val text: String
) {
    NOW_PLAYING_MOVIE("Now Playing"),
    POPULAR_MOVIE("Popular"),
    TOP_RATED_MOVIE("Top Rated"),
    UPCOMING_MOVIE("Upcoming"),

    AIRING_TODAY_TV("Airing Today"),
    TOP_RATED_TV("Top Rated"),
    ON_THE_AIR_TV("On The Air"),
    POPULAR_TV("Popular");
}