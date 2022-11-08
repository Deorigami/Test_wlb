package com.ardinata.test.test_goplay.core.extension

import android.app.Activity
import android.view.inputmethod.InputMethodManager


fun Activity.hideKeyboard(){
    val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}