package com.ardinata.test.test_goplay.template.group.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ardinata.test.test_goplay.organism.CardItemView

class CardGroupAdapter(
    private val onFavButtonPressed : (Int) -> Unit = {}
) : RecyclerView.Adapter<CardGroupAdapter.ViewHolder>(){

    var items = listOf<CardItemView.Data>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(
        val view: CardItemView,
        private val onFavButtonPressed: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        fun onBind(data: CardItemView.Data, position: Int) {
            view.apply {
                imagePoster = data.imagePoster
                title = data.title
                setOnClickListener { onFavButtonPressed.invoke(position) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardItemView(parent.context)
        val vh = ViewHolder(binding, onFavButtonPressed)
        vh.view.layoutParams = ViewGroup.LayoutParams(
            parent.layoutParams.width,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position], position)
    }

    override fun getItemCount(): Int = items.size
}