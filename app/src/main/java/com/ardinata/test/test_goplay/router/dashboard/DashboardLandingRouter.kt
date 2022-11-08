package com.ardinata.test.test_goplay.router.dashboard

import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.detail.DetailActivity
import com.ardinata.feature_dashboard.detail.MovieDBDetailPage.Companion.MOVIE_ITEM
import com.ardinata.feature_dashboard.detail.MovieDBDetailPage.Companion.TV_ITEM
import com.ardinata.feature_dashboard.landing.DashboardLandingActivity
import com.ardinata.service_movie_db.domain.entity.MovieListItemEntity
import com.ardinata.service_movie_db.domain.entity.TVListItemEntity
import com.ardinata.test.test_goplay.core.base.BaseRouter
import javax.inject.Inject

class DashboardLandingRouter @Inject constructor() : BaseRouter(), DashboardLandingContract.Router {

    override fun navigateToDashboard(context: Context) {
        val intent = Intent(context, DashboardLandingActivity::class.java)
        context.startActivity(intent)
    }

    override fun navigateToMovieDetailPage(
        context: Context,
        movieItem: MovieListItemEntity?,
        tvListItemEntity: TVListItemEntity?
    ) {
        val intent = Intent(context, DetailActivity::class.java).putExtras(bundleOf(
            MOVIE_ITEM to movieItem,
            TV_ITEM to tvListItemEntity
        ))
        context.startActivity(intent)
    }
}