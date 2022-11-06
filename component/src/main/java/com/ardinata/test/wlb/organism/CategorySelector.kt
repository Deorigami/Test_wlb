package com.ardinata.test.wlb.organism

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.marginEnd
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.ardinata.component.databinding.OrganismMerchantFilterGroupBinding
import com.ardinata.test.wlb.util.BaseRecyclerViewAdapter
import com.ardinata.test.wlb.util.dp

class CategorySelector(
    context: Context,
    attrs : AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = OrganismMerchantFilterGroupBinding.inflate(LayoutInflater.from(context), this, true)

    private val adapter = object : BaseRecyclerViewAdapter<TabsCustomItem.Data, TabsCustomItem>(
        width = RecyclerView.LayoutParams.WRAP_CONTENT
    ) {
        override fun ViewHolder<TabsCustomItem>.onBind(item: TabsCustomItem.Data, position: Int) {
            view.apply {
                title = item.title
                isActive = item.isActive
                setOnClickListener {
                    setSelectedTab(position)
                }
            }
        }

        override fun registerView(): TabsCustomItem = TabsCustomItem(context)

    }

    var items = mutableListOf<TabsCustomItem.Data>()
        set(value){
            field = value
            adapter.items = value
        }

    var isCategoryCanRefreshView = true
        set(value){
            field = value
            refreshView()
        }


    var onPressed : (Int) -> Unit = {}
        set(value){
            field = value
            refreshView()
        }

    init {
        binding.root.apply {
            val padding = this@CategorySelector.paddingStart
            setPadding(padding, 0, padding, 0)
            clipChildren= false
            clipToPadding = false
            adapter = this@CategorySelector.adapter.also { it.items = this@CategorySelector.items }
            layoutManager = object : LinearLayoutManager(context, HORIZONTAL, false) {
                override fun smoothScrollToPosition(
                    recyclerView: RecyclerView?,
                    state: RecyclerView.State?,
                    position: Int
                ) {
                    val smoothScroller = object : LinearSmoothScroller(context){
                        val speed = 50f
                        override fun getHorizontalSnapPreference(): Int {
                            return SNAP_TO_START
                        }
                        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
                            return speed / resources.displayMetrics.densityDpi
                        }
                    }
                    smoothScroller.targetPosition = position
                    startSmoothScroll(smoothScroller)
                }
            }
            itemAnimator = null
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    if (parent.getChildAdapterPosition(view) == items.lastIndex) {
                        outRect.right = (resources.displayMetrics.widthPixels - view.measuredWidth - (24.dp(context) * 2))
                    }
                }
            })
        }
        setPadding(0,0,0,0)
    }

    fun setSelectedTab(i : Int?, invokeOnPressed : Boolean = true){
        if (invokeOnPressed) onPressed.invoke(i ?: -1)
        if (i != null) {
            if (isCategoryCanRefreshView) {
                refreshAdapter(i)
                val lm = binding.root.layoutManager
                if (lm is LinearLayoutManager) {
                    lm.smoothScrollToPosition(binding.root, RecyclerView.State(), i)
                }
            }
        }
    }

    private fun refreshAdapter(i : Int){
        val newItems = items.mapIndexed { index, data ->
            data.copy(
                isActive = i == index,
            )
        }.toMutableList()
        items = newItems
    }

    private fun refreshView() {

    }

}