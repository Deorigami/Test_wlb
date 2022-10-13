package com.ardinata.test.wlb.core.util

import android.content.Context
import com.ardinata.test.wlb.config.constant.Config
import com.ardinata.test.wlb.template.SortFilter

object SessionManager {
    fun getSortPreference(context: Context): SortFilter.Companion.SortType {
        val cache = CacheUtil.get(
            context,
            Config.SORT_PREFERENCE,
            SortFilter.Companion.SortType.ASCENDING.name,
            Config.SHARED_PREFERENCE_NAME_UNCLEARABLE
        )
        return SortFilter.Companion.SortType.valueOf(cache)
    }
    fun setSortPreference(context: Context, pref: SortFilter.Companion.SortType) {
        CacheUtil.set(
            context = context,
            key = Config.SORT_PREFERENCE,
            value = pref.name,
            configName = Config.SHARED_PREFERENCE_NAME_UNCLEARABLE
        )
    }
}