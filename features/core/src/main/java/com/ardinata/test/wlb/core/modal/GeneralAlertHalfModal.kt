package com.ardinata.test.wlb.core.modal

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.view.isVisible
import com.ardinata.core.R
import com.ardinata.core.databinding.GeneralHalfModalBinding
import com.ardinata.test.wlb.core.base.BaseCustomHeightViewBindingBottomSheetDialog
import com.ardinata.test.wlb.util.customSetImage
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso

class GeneralAlertHalfModal(
    private val title:String,
    private val description:String,
    private val imageResource:Any? = R.drawable.ic_visual_too_many_request,
    private val primaryButtonTitle:String,
    private val onPrimaryButtonClick:(()->Unit)? = null,
    private val secondaryButtonTitle : String = "",
    private val onSecondaryButtonClick : (() -> Unit)? = null,
    private val isDismissable:Boolean = false,
    override val layout: Int = R.layout.general_half_modal,
    override var bottomSheetMode: Companion.BottomSheetMode = Companion.BottomSheetMode.FULL
) : BaseCustomHeightViewBindingBottomSheetDialog<GeneralHalfModalBinding>() {

    override fun initBinding(view: View) {
        binding = GeneralHalfModalBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        initView()
    }

    private fun initView() {
        binding?.tvTitle?.text = title
        binding?.tvDescription?.text = description
        binding?.btnPrimary?.text = primaryButtonTitle
        binding?.btnPrimary?.setOnClickListener {
            dismiss()
            onPrimaryButtonClick?.invoke()
        }
        binding?.btnSecondary?.text = secondaryButtonTitle
        binding?.btnSecondary?.setOnClickListener {
            dismiss()
            onSecondaryButtonClick?.invoke()
        }

        binding?.btnSecondary?.isVisible = secondaryButtonTitle.isNotEmpty()

        isCancelable = isDismissable

        imageResource?.let {
            binding?.ivRight?.customSetImage(imageResource)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val showListener = DialogInterface.OnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout = bottomSheetDialog.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            )
            parentLayout?.let { bottomSheet ->
                bottomSheet.background = ColorDrawable(Color.TRANSPARENT)
                val behaviour = BottomSheetBehavior.from(bottomSheet)
                val layoutParams = bottomSheet.layoutParams
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
                bottomSheet.layoutParams = layoutParams
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
                behaviour.isDraggable = false
            }
        }
        dialog.setOnShowListener(showListener)
        return dialog
    }
}