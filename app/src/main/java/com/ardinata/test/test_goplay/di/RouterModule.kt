package com.ardinata.test.test_goplay.di

import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.test.test_goplay.router.dashboard.DashboardLandingRouter
import com.ardinata.test.test_goplay.router.splash.SplashRouterImpl
import com.ardinata.test.test_goplay.splash.SplashContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RouterModule {

    @Binds
    abstract fun provideDashboardLandingRouter(x: DashboardLandingRouter) : DashboardLandingContract.Router

    @Binds
    abstract fun bindsSplashRouter(r: SplashRouterImpl) : SplashContract.Router
}