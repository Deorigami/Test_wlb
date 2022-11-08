package com.ardinata.test.test_goplay.util

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<I, V: View>(

) : RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder<V>>(){

    private var widthParam = RecyclerView.LayoutParams.MATCH_PARENT
    private var heightParam = RecyclerView.LayoutParams.WRAP_CONTENT

    constructor(width : Int) : this(){
        this.widthParam = width
    }

    constructor(width: Int, heigth: Int) : this(width){
        this.widthParam = width
        this.heightParam = heigth
    }

    var items : List<I> = listOf()
        set(value) {
            field = value
            notifyItemRangeChanged(0, value.size)
        }

    abstract fun ViewHolder<V>.onBind(item: I, position: Int)
    abstract fun registerView() : V

    class ViewHolder<V : View>(
        val view: V
    ) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<V> {
        val vh = ViewHolder(registerView())
        vh.apply {
            view.layoutParams = ViewGroup.LayoutParams(
                widthParam,
                heightParam
            )
        }
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder<V>, position: Int) {
        holder.onBind(items[position], position)
    }

    override fun getItemCount(): Int = items.size
}