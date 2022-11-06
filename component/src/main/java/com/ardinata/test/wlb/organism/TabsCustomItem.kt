package com.ardinata.test.wlb.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.ardinata.component.R
import com.ardinata.component.databinding.MoleculeTabLayoutItemBinding
import com.ardinata.test.wlb.util.setCompatibleTextAppearance

class TabsCustomItem(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = MoleculeTabLayoutItemBinding.inflate(LayoutInflater.from(context), this, true)

    data class Data(
        val title : String,
        val isActive : Boolean = false,
    )

    var title = ""
        set(value){
            field = value
            refreshView()
        }

    var isActive = false
        set(value){
            field = value
            refreshView()
        }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.root.setOnClickListener(l)
    }

    private fun refreshView(){
        binding.tv.text = title
        binding.indicator.visibility = if (isActive) View.VISIBLE else View.INVISIBLE
        if (isActive) binding.tv.setCompatibleTextAppearance(R.style.TextAppearance_BoldS)
        else binding.tv.setCompatibleTextAppearance(R.style.TextAppearance_CaptionL)
    }
}