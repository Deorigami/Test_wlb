package com.ardinata.test.wlb.template

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.ardinata.component.databinding.GroupBaseLayoutBinding
import com.ardinata.test.wlb.atom.TextItem
import com.ardinata.test.wlb.organism.FilterGroupSegment
import com.ardinata.test.wlb.util.BaseRecyclerViewAdapter

class SegmentedFilterGroup(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = GroupBaseLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    private val adapter = object : BaseRecyclerViewAdapter<FilterGroupSegment.Data, FilterGroupSegment>() {
        override fun ViewHolder<FilterGroupSegment>.onBind(
            item: FilterGroupSegment.Data,
            position: Int
        ) {
            view.apply {
                segmentTitle = item.segmentTitle
                segmentList = item.segmentList.toMutableList()
                onSegmentItemPressed = {
                    onFilterItemPressed.invoke(position, it)
                }
            }
        }

        override fun registerView(): FilterGroupSegment = FilterGroupSegment(context)
    }

    var onFilterItemPressed : (parentIdx : Int, childIdx: Int) -> Unit = { _, _ -> }
        set(value){
            field = value
            refreshView()
        }

    var segmentedFilterList = mutableListOf<FilterGroupSegment.Data>()
        set(value){
            field = value
            adapter.items = value
        }

    init {
        binding.rv.apply {
            adapter = this@SegmentedFilterGroup.adapter.also { it.items = segmentedFilterList }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }


    private fun refreshView() {

    }

}