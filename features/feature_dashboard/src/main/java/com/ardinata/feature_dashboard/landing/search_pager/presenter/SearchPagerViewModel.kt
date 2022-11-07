package com.ardinata.feature_dashboard.landing.search_pager.presenter

import com.ardinata.test.wlb.core.base.BaseViewModel
import com.ardinata.test.wlb.core.extension.StatefulLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchPagerViewModel @Inject constructor(

) : BaseViewModel(){
    override fun getKillableStatefulLiveData(): List<StatefulLiveData<*, *>> {
        return listOf()
    }
}