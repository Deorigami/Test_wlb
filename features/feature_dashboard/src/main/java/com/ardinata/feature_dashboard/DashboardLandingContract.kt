package com.ardinata.feature_dashboard

import android.content.Context
import com.ardinata.test.wlb.core.contract.RouterContract
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity

interface DashboardLandingContract {
    interface Router : RouterContract {
        fun navigateToDashboard(context: Context)
        fun navigateToGameDetail(context: Context, drinkItem: CocktailDrinkItemEntity)
    }
}