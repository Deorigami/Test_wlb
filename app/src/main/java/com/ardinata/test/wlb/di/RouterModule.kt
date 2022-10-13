package com.ardinata.test.wlb.di

import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.test.wlb.router.dashboard.DashboardLandingRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RouterModule {

    @Binds
    abstract fun provideDashboardLandingRouter(x: DashboardLandingRouter) : DashboardLandingContract.Router
}