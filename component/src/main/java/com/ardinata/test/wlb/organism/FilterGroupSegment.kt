package com.ardinata.test.wlb.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ardinata.component.databinding.OrganismFilterGroupSegmentBinding
import com.ardinata.test.wlb.atom.TextItem

class FilterGroupSegment(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = OrganismFilterGroupSegmentBinding.inflate(LayoutInflater.from(context), this, true)

    data class Data(
        val segmentTitle: String,
        val segmentList : List<TextItem.Data> = emptyList()
    )

    var segmentTitle = ""
        set(value){
            field = value
            refreshView()
        }

    var segmentList = mutableListOf<TextItem.Data>()
        set(value){
            field = value
            refreshView()
        }

    var onSegmentItemPressed : (Int) -> Unit = {}
        set(value){
            field = value
            refreshView()
        }


    private fun refreshView() {
        binding.segmentTitle.text = segmentTitle
        binding.filterGroup.apply {
            items = segmentList
            onPressed = onSegmentItemPressed
        }
    }

}