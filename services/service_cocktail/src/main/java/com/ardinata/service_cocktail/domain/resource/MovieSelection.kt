package com.ardinata.service_cocktail.domain.resource

enum class MOVIESECTION(
    val text: String
) {
    NOW_PLAYING_MOVIE("Now Playing"),
    POPULAR_MOVIE("Popular"),
    TOP_RATED_MOVIE("Top Rated"),
    UPCOMING_MOVIE("Upcoming"),
    NOW_PLAYING_TV("Now Playing"),
    POPULAR_TV("Popular"),
    TOP_RATED_TV("Top Rated"),
    UPCOMING_TV("Upcoming");
}