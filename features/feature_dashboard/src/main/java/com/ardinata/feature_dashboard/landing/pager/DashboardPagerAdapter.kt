package com.ardinata.feature_dashboard.landing.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class DashboardPagerAdapter(
    fm: FragmentManager,
    lf: Lifecycle,
    private vararg val fragments: Fragment
) : FragmentStateAdapter(fm, lf) {
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}