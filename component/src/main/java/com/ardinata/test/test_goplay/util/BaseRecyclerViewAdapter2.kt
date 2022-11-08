package com.ardinata.test.test_goplay.util

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class  BaseRecyclerViewAdapter2<ITEM : Any,V : View>(

) : ListAdapter<ITEM, BaseRecyclerViewAdapter2.ViewHolder<V>>(
    object : DiffUtil.ItemCallback<ITEM>() {
        override fun areItemsTheSame(oldItem: ITEM, newItem: ITEM): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ITEM, newItem: ITEM): Boolean {
            return oldItem == newItem
        }
    }
) {

    abstract fun ViewHolder<V>.onBind(item: ITEM, position: Int)
    open fun onItemPressed(index: Int){}
    abstract fun generateView(viewType: Int) : V

    class ViewHolder<V: View>(
        val view: V
    ): RecyclerView.ViewHolder(view)

    constructor(width : Int) : this(){
        this.widthParam = width
    }

    constructor(width: Int, heigth: Int) : this(width){
        this.widthParam = width
        this.heightParam = heigth
    }

    private var widthParam = RecyclerView.LayoutParams.MATCH_PARENT
    private var heightParam = RecyclerView.LayoutParams.WRAP_CONTENT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<V> {
        val vh = ViewHolder(
            generateView(viewType)
        ).apply {
            view.layoutParams = ViewGroup.LayoutParams(
                widthParam,
                heightParam
            )
        }
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder<V>, position: Int) {
        holder.apply {
            onBind(getItem(position), position)
            itemView.setOnClickListener { onItemPressed(position) }
        }
    }


}