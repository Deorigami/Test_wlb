package com.ardinata.test.test_goplay.core.base

import androidx.lifecycle.ViewModel
import com.ardinata.test.test_goplay.core.extension.StatefulLiveData

abstract class BaseViewModel : ViewModel() {

    override fun onCleared() {
        val killables = getKillableStatefulLiveData()
        killables.forEach {
            it.cancel()
        }

        super.onCleared()
    }

    abstract fun getKillableStatefulLiveData(): List<StatefulLiveData<*, *>>
}