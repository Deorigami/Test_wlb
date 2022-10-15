package com.ardinata.test.wlb.template

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ardinata.component.databinding.HomeTabCustomLayoutBinding
import com.ardinata.test.wlb.util.customSetImage

class TabsItem(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = HomeTabCustomLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    data class Data(
        val activeIcon : Any? = null,
        val inactiveIcon : Any? = null,
        val activeColor: Int? = null,
        val inactiveColor: Int? = null,
        val title: String,
        val shouldTintActiveIcon : Boolean = true
    )

    var data : Data? = null
        set(value) {
            field = value
            data?.let {
                activeColor = it.activeColor
                inactiveColor = it.inactiveColor
                activeIcon = it.activeIcon
                inactiveIcon = it.inactiveIcon
                title = it.title
                shouldTintActiveIcon = it.shouldTintActiveIcon
            }
        }

    var activeColor: Int? = null
        set(value){
            field = value
            refreshView()
        }

    var inactiveColor: Int? = null
        set(value){
            field = value
            refreshView()
        }

    var activeIcon: Any? = null
        set(value){
            field = value
            refreshView()
        }

    var inactiveIcon: Any? = null
        set(value){
            field = value
            refreshView()
        }

    var title = ""
        set(value){
            field = value
            refreshView()
        }

    var isActive = false
        set(value){
            field = value
            refreshView()
        }

    var shouldTintActiveIcon = false
        set(value){
            field = value
            refreshView()
        }

    private fun refreshView() {
        if (isActive){
            binding.tabIcon.apply {
                customSetImage(activeIcon)
                if (shouldTintActiveIcon) imageTintList = activeColor?.let { ColorStateList.valueOf(it) }
            }
            activeColor?.let { binding.tabTitle.setTextColor(it) }
        } else {
            binding.tabIcon.apply {
                customSetImage(inactiveIcon)
                if (shouldTintActiveIcon) imageTintList = inactiveColor?.let { ColorStateList.valueOf(it) }
            }
            inactiveColor?.let { binding.tabTitle.setTextColor(it) }
        }
        binding.tabTitle.text = title
    }


}