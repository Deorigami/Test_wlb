package com.ardinata.test.test_goplay.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ardinata.component.databinding.MoleculeIngredientsItemBinding
import com.ardinata.test.test_goplay.util.customSetImage

class IngredientItem(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){

    private val binding = MoleculeIngredientsItemBinding.inflate(LayoutInflater.from(context), this, true)

    data class Data(
        val image: String,
        val title: String,
        val measure: String
    )

    var image = ""
        set(value){
            field = value
            refreshView()
        }

    var title = ""
        set(value){
            field = value
            refreshView()
        }

    var measure = ""
        set(value){
            field = value
            refreshView()
        }

    private fun refreshView() {
        binding.image.customSetImage(image)
        binding.ingredientTitle.text = title
        binding.ingredientMeasure.text = measure
    }

}