package com.ardinata.test.test_goplay.template

import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.ardinata.component.databinding.GroupBaseLayoutBinding
import com.ardinata.test.test_goplay.molecule.IngredientItem
import com.ardinata.test.test_goplay.util.BaseRecyclerViewAdapter
import com.ardinata.test.test_goplay.util.BaseRecyclerViewAdapter2

class IngredientsGroup(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = GroupBaseLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    private val adapter = object : BaseRecyclerViewAdapter2<IngredientItem.Data, IngredientItem>(
        width = RecyclerView.LayoutParams.WRAP_CONTENT,
        heigth = RecyclerView.LayoutParams.MATCH_PARENT
    ) {
        override fun ViewHolder<IngredientItem>.onBind(item: IngredientItem.Data, position: Int) {
            view.apply {
                title = item.title
                image = item.image
                measure = item.measure
            }
        }

        override fun generateView(viewType: Int): IngredientItem = IngredientItem(context)
    }

    var items = mutableListOf<IngredientItem.Data>()
        set(value){
            field = value
            adapter.submitList(value)
        }

    init {
        clipChildren = false
        clipToPadding = false
        binding.rv.apply {
            val paddingStart = this@IngredientsGroup.paddingStart
            val paddingEnd = this@IngredientsGroup.paddingEnd
            updatePadding(left = paddingStart, right = paddingEnd, top = paddingStart, bottom = paddingStart)
            adapter = this@IngredientsGroup.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = null
        }

        // Setting padding to 0 then set padding to the rv to prevent shadow issues on new android 13 when the item strecth like ios
        setPadding(0,0,0,0)
    }
}