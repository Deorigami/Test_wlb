package com.ardinata.feature_dashboard.landing.mapper

import android.content.Context
import androidx.core.content.ContextCompat
import com.ardinata.feature_dashboard.R
import com.ardinata.test.wlb.template.TabsItem

class TabsItemMapper {
    operator fun invoke(
        context: Context,
        position : Int
    ) : TabsItem {
        return TabsItem(context).apply {
            when (position) {
                0 -> data = TabsItem.Data(
                    activeIcon = ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_movie_filled
                    ),
                    inactiveIcon = ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_movie
                    ),
                    activeColor = ContextCompat.getColor(context, R.color.brightSkyBlue),
                    inactiveColor = ContextCompat.getColor(
                        context,
                        R.color.battleshipGrey
                    ),
                    title = "Movie",
                ).apply {
                    isActive = true
                }
                1 -> data = TabsItem.Data(
                    activeIcon = ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_tv_filled
                    ),
                    inactiveIcon = ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_tv
                    ),
                    activeColor = ContextCompat.getColor(context, R.color.brightSkyBlue),
                    inactiveColor = ContextCompat.getColor(
                        context,
                        R.color.battleshipGrey
                    ),
                    title = "Series"
                )
                2 -> data = TabsItem.Data(
                    activeIcon = ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_search_filled
                    ),
                    inactiveIcon = ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_search
                    ),
                    activeColor = ContextCompat.getColor(context, R.color.brightSkyBlue),
                    inactiveColor = ContextCompat.getColor(
                        context,
                        R.color.battleshipGrey
                    ),
                    title = "Search"
                )
                3 -> data = TabsItem.Data(
                    activeIcon = ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_heart_filled
                    ),
                    inactiveIcon = ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_heart_base_black
                    ),
                    activeColor = ContextCompat.getColor(context, R.color.brightSkyBlue),
                    inactiveColor = ContextCompat.getColor(
                        context,
                        R.color.battleshipGrey
                    ),
                    title = "Favorite",
                )
                else -> { return@apply }
            }
        }
    }
}