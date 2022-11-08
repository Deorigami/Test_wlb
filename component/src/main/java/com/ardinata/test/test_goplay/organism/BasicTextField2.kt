package com.ardinata.test.test_goplay.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ardinata.component.databinding.OrganismBasicTextField2Binding

class BasicTextField2(
    context: Context,
    attrs: AttributeSet
) : LinearLayout(context, attrs){
    private val binding = OrganismBasicTextField2Binding.inflate(LayoutInflater.from(context), this, true)
}