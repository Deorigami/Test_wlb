package com.ardinata.test.wlb.template

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.ardinata.component.R
import com.ardinata.component.databinding.TemplateHeaderBinding
import com.ardinata.test.wlb.util.customSetImage

class SimpleHeader(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = TemplateHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    var title = ""
        set(value){
            field = value
            refreshView()
        }

    var onBackPressed : () -> Unit = {}
        set(value){
            field = value
            refreshView()
        }

    var hasFavButton = true
        set(value){
            field = value
            refreshView()
        }

    var hasBackButton = true
        set(value){
            field = value
            refreshView()
        }

    var isFav = false
        set(value){
            field = value
            onFavButtonChangeListener.invoke(value)
            refreshView()
        }

    var onFavButtonPressed : () -> Unit = {}
        set(value){
            field = value
            refreshView()
        }

    var onFavButtonChangeListener : (Boolean) -> Unit = {}
        set(value){
            field = value
            refreshView()
        }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleHeader)
        typedArray.run {
            getString(R.styleable.SimpleHeader_title)?.let { title =  it }
            getBoolean(R.styleable.SimpleHeader_hasFavButton, true).let { hasFavButton = it }
            getBoolean(R.styleable.SimpleHeader_hasBackButton, true).let { hasBackButton = it }
        }

        typedArray.recycle()
        binding.backIcon.setOnClickListener { onBackPressed() }
        binding.favButton.setOnClickListener {
            isFav = !isFav
            onFavButtonPressed()
        }
    }

    private fun refreshView() {
        binding.title.text = title
        binding.favButton.customSetImage( ContextCompat.getDrawable(context, if (isFav) R.drawable.ic_heart_filled else R.drawable.ic_heart_base) )
        binding.favButton.isVisible = hasFavButton
        binding.backIcon.isVisible = hasBackButton
    }

}