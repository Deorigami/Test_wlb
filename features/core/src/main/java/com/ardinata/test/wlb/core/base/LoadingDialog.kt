package com.ardinata.test.wlb.core.base

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.ardinata.core.R
import com.ardinata.core.databinding.LoadingAlertLayoutBinding
import com.bumptech.glide.Glide

class LoadingDialog(
    private val onCanceled : (() -> Unit)? = null
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        
        val alert = AlertDialog.Builder(requireContext())
        val loadingAlertBinding = LoadingAlertLayoutBinding.inflate(layoutInflater)
        val loadingDialog = alert.create()

        loadingAlertBinding.apply {
            Glide.with(requireContext()).load(R.raw.loading_transparent).into(loadingImageView)
        }

        loadingDialog.setView(loadingAlertBinding.root)
        loadingDialog.setCancelable(false)
        loadingDialog.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        return loadingDialog
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        onCanceled?.invoke()
    }

    companion object {
        fun newInstance() = LoadingDialog().apply {
            arguments = bundleOf(

            )
        }
    }
}