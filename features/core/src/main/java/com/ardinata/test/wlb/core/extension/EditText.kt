package com.ardinata.test.wlb.core.extension

import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

@CheckResult
fun EditText.textChanges() : Flow<CharSequence?> {
    return callbackFlow {
        val listener = doAfterTextChanged {
            trySend(it)
        }
        awaitClose { removeTextChangedListener(listener) }
    }.onStart {
        emit(text)
    }
}