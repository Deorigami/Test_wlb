package com.ardinata.test.test_goplay.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ardinata.component.databinding.OrganismCardItemBinding
import com.ardinata.test.test_goplay.util.customSetImage

class CardItemView(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = OrganismCardItemBinding.inflate(LayoutInflater.from(context), this, true)

    data class Data(
        val imagePoster: Any? = null,
        val title: String = "",
    )

    var imagePoster: Any? = null
        set(value) {
            field = value
            refreshView()
        }

    var title: String = ""
        set(value) {
            field = value
            refreshView()
        }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.root.setOnClickListener(l)
    }

    private fun refreshView(){
        binding.apply {
            leftImage.customSetImage(imagePoster)
            title.text = this@CardItemView.title
        }
    }
}