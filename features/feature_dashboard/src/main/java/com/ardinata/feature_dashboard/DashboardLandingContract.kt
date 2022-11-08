package com.ardinata.feature_dashboard

import android.content.Context
import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import com.ardinata.service_movie_db.domain.entity.TVListItemEntity
import com.ardinata.test.test_goplay.core.contract.RouterContract

interface DashboardLandingContract {
    interface Router : RouterContract {
        fun navigateToDashboard(context: Context)
        fun navigateToMovieDetailPage(context: Context, movieItem: MovieListItemEntity? = null, tvListItemEntity: TVListItemEntity? = null)
    }
}