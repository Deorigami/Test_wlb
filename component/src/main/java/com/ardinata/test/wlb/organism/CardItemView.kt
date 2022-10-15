package com.ardinata.test.wlb.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ardinata.component.databinding.OrganismCardItemBinding
import com.ardinata.test.wlb.util.customSetImage

class CardItemView(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = OrganismCardItemBinding.inflate(LayoutInflater.from(context), this, true)

    data class Data(
        val imagePoster: Any? = null,
        val title: String = "",
        val category : String,
        val glass: String,
        val alcohol: String
    )

    var imagePoster: Any? = null
        set(value) {
            field = value
            refreshView()
        }

    var category: String = ""
        set(value) {
            field = value
            refreshView()
        }

    var alcohol: String = ""
        set(value) {
            field = value
            refreshView()
        }

    var glass: String = ""
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
            glass.text = this@CardItemView.glass
            category.text = this@CardItemView.category
            alcohol.text = this@CardItemView.alcohol
        }
    }
}