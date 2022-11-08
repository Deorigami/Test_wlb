package com.ardinata.feature_dashboard.landing.modal

import android.content.DialogInterface
import android.view.View
import android.view.WindowManager
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.ModalFilterBinding
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.test.test_goplay.core.base.BaseCustomHeightViewBindingBottomSheetDialog
import com.ardinata.test.test_goplay.core.extension.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterModal(
    override var bottomSheetMode: Companion.BottomSheetMode = Companion.BottomSheetMode.ALMOST_FULL,
    override val layout: Int = R.layout.modal_filter
) : BaseCustomHeightViewBindingBottomSheetDialog<ModalFilterBinding>(){

    private val viewModel : DashboardViewModel by activityViewModels()

    override fun initBinding(view: View) {
        binding = ModalFilterBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setObserver()
        setListener()
        initView()
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    private fun initView() {

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        requireActivity().hideKeyboard()
    }

    private fun setObserver() {
        viewModel.run {

        }
    }

    private fun setListener() {
        binding?.filterGroup?.apply {
            onFilterItemPressed = { parentIdx, childIdx ->

            }
        }
        binding?.button?.setOnClickListener { dismiss() }
        binding?.textFieldView?.textField?.doAfterTextChanged {  }
    }


}