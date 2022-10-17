package com.ardinata.test.wlb.router.dashboard

import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import com.ardinata.test.wlb.core.base.BaseRouter
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.detail.DrinkDetailActivity
import com.ardinata.feature_dashboard.detail.DrinkDetailPage
import com.ardinata.feature_dashboard.landing.DashboardLandingActivity
import com.ardinata.service_cocktail.domain.entity.CocktailDrinkItemEntity
import javax.inject.Inject

class DashboardLandingRouter @Inject constructor() : BaseRouter(), DashboardLandingContract.Router {

    override fun navigateToDashboard(context: Context) {
        val intent = Intent(context, DashboardLandingActivity::class.java)
        context.startActivity(intent)
    }

    override fun navigateToGameDetail(context: Context, drinkItem: CocktailDrinkItemEntity) {
        val intent = Intent(context, DrinkDetailActivity::class.java)
        intent.putExtras(bundleOf(
            DrinkDetailPage.DRINK_ITEM to drinkItem
        ))
        context.startActivity(intent)
    }
}