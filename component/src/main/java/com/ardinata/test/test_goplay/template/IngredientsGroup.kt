package com.ardinata.test.test_goplay.template

import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ardinata.component.databinding.GroupBaseLayoutBinding
import com.ardinata.test.test_goplay.molecule.IngredientItem
import com.ardinata.test.test_goplay.util.BaseRecyclerViewAdapter

class IngredientsGroup(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = GroupBaseLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    private val adapter = object : BaseRecyclerViewAdapter<IngredientItem.Data, IngredientItem>() {
        override fun ViewHolder<IngredientItem>.onBind(item: IngredientItem.Data, position: Int) {
            view.apply {
                title = item.title
                image = item.image
                measure = item.measure
            }
        }

        override fun registerView(): IngredientItem = IngredientItem(context)
    }

    var items = mutableListOf<IngredientItem.Data>()
        set(value){
            field = value
            adapter.items = value
        }

    init {
        clipChildren = false
        clipToPadding = false
        binding.rv.apply {
            val paddingStart = this@IngredientsGroup.paddingStart
            val paddingEnd = this@IngredientsGroup.paddingEnd
            updatePadding(left = paddingStart, right = paddingEnd, top = paddingStart, bottom = paddingStart)
            adapter = this@IngredientsGroup.adapter.also { it.items = items }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = null
        }

        // Setting padding to 0 then set padding to the rv to prevent shadow issues on new android 13 when the item strecth like ios
        setPadding(0,0,0,0)
    }
}