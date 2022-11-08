package com.ardinata.test.test_goplay.core.extension

import androidx.lifecycle.MutableLiveData

class NonNullMutableLiveData<T: Any>(
    private val initialValue: T
) : MutableLiveData<T>() {

    override fun getValue(): T = super.getValue() ?: initialValue
}