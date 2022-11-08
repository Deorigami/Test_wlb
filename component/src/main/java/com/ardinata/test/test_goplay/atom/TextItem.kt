package com.ardinata.test.test_goplay.atom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ardinata.component.databinding.AtomTextItemBinding

class TextItem(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = AtomTextItemBinding.inflate(LayoutInflater.from(context), this, true)

    data class Data(
        val text: String,
        val isActive: Boolean = false
    )

    var isActive = false
        set(value){
            field = value
            refreshView()
        }

    var text = ""
        set(value){
            field = value
            refreshView()
        }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.textView.setOnClickListener(l)
    }

    private fun refreshView() {
        binding.apply {
            textView.isSelected = isActive
            textView.text = text
        }
    }

}