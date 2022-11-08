package com.ardinata.test.test_goplay.template.group

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ardinata.component.databinding.GroupBaseLayoutBinding
import com.ardinata.test.test_goplay.organism.CardItemView
import com.ardinata.test.test_goplay.util.BaseRecyclerViewAdapter2
import com.ardinata.test.test_goplay.util.dp

class CardGroup(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = GroupBaseLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    private val adapter = object : BaseRecyclerViewAdapter2<CardItemView.Data, CardItemView>() {
        override fun ViewHolder<CardItemView>.onBind(item: CardItemView.Data, position: Int) {
            view.apply {
                imagePoster = item.imagePoster
                title = item.title
                setOnClickListener { onCardPressed.invoke(position) }
            }
        }

        override fun onItemPressed(index: Int) {
            onCardPressed.invoke(index)
        }

        override fun generateView(viewType: Int): CardItemView = CardItemView(context)

    }

    var items = mutableListOf<CardItemView.Data>()
        set(value){
            field = value
            adapter.submitList(value)
        }

    var onFinishScrolling : () -> Unit = {}
        set(value){
            field = value
            refreshView()
        }

    var onCardPressed : (Int) -> Unit = {}
        set(value){
            field = value
            refreshView()
        }



    init {
        clipChildren = false
        clipToPadding = false

        binding.rv.apply {
            val paddingStart = this@CardGroup.paddingStart
            val paddingEnd = this@CardGroup.paddingEnd
            updatePadding(left = paddingStart, right = paddingEnd, top = paddingStart, bottom = paddingStart)
            adapter = this@CardGroup.adapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            requestDisallowInterceptTouchEvent(false)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(rv: RecyclerView, newState: Int) {
                    if (!rv.canScrollVertically(1) && newState == 0){
                        onFinishScrolling.invoke()
                    }
                }
            })
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    view.updateLayoutParams<MarginLayoutParams> {
                        marginStart = 10.dp(context)
                    }
                }
            })
        }

        // Setting padding to 0 then set padding to the rv to prevent shadow issues on new android 13 when the item strecth like ios
        setPadding(0,0,0,0)
    }

    private fun refreshView() {

    }
}