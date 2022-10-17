package com.ardinata.test.wlb.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ardinata.component.databinding.GroupBaseLayoutBinding
import com.ardinata.test.wlb.atom.TextItem
import com.ardinata.test.wlb.util.BaseRecyclerViewAdapter
import com.ardinata.test.wlb.util.SafeFlexboxLayoutManager
import com.google.android.flexbox.FlexboxLayoutManager

class FilterGroup(
    context: Context,
    attrs: AttributeSet?= null
) : LinearLayout(context, attrs){
    private val binding = GroupBaseLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    private val adapter = object : BaseRecyclerViewAdapter<TextItem.Data, TextItem>(
        width = FlexboxLayoutManager.LayoutParams.WRAP_CONTENT,
        heigth = FlexboxLayoutManager.LayoutParams.WRAP_CONTENT
    ) {
        override fun ViewHolder<TextItem>.onBind(item: TextItem.Data, position: Int) {
            view.apply {
                isActive = item.isActive
                text = item.text
                setOnClickListener { refreshAdapter(position) }
            }
        }

        override fun registerView(): TextItem = TextItem(context)
    }

    var items : MutableList<TextItem.Data> = mutableListOf()
        set(value){
            field = value
            adapter.items = value
        }

    var onPressed : (Int) -> Unit = {}
        set(value){
            field = value
            refreshView()
        }


    init {
        binding.rv.apply {
            val flexManager = SafeFlexboxLayoutManager(context)
            adapter = this@FilterGroup.adapter.also { it.items = items }
            layoutManager = flexManager
            itemAnimator = null
        }
    }


    private fun refreshView() {
        // refresh view
    }

    private fun refreshAdapter(idx: Int){
        onPressed.invoke(idx)
        val newItem = items.mapIndexed { index, data ->
            data.copy(isActive = if (index == idx) !data.isActive else data.isActive)
        }
        items = newItem.toMutableList()
    }
}