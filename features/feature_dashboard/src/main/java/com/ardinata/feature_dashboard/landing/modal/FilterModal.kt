package com.ardinata.feature_dashboard.landing.modal

import android.content.DialogInterface
import android.view.View
import android.view.WindowManager
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.ModalFilterBinding
import com.ardinata.feature_dashboard.landing.pager.PagerDrinkList
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.test.wlb.atom.TextItem
import com.ardinata.test.wlb.core.base.BaseCustomHeightViewBindingBottomSheetDialog
import com.ardinata.test.wlb.core.extension.hideKeyboard
import com.ardinata.test.wlb.organism.FilterGroupSegment
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
        viewModel.nameFilter.value.let {
            if (it.isNotEmpty()) binding?.textFieldView?.textField?.setText(it)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        requireActivity().hideKeyboard()
    }

    private fun setObserver() {
        viewModel.run {
            filterData.observe(viewLifecycleOwner){ list ->
                lifecycleScope.launchWhenResumed {
                    list.map { filterEntity ->
                        FilterGroupSegment.Data(
                            segmentTitle = filterEntity.filterType.name,
                            segmentList = filterEntity.filterList.map { TextItem.Data(
                                text = it,
                                isActive = getSelectedFilterList().contains(it)
                            ) }
                        ) }.toMutableList().let {
                        binding?.filterGroup?.segmentedFilterList = it
                    }
                }
            }
            filteredCocktailList.observe(viewLifecycleOwner){
                binding?.button?.text = "${it.size} Ditemukan"
            }
        }
    }

    private fun setListener() {
        binding?.filterGroup?.apply {
            onFilterItemPressed = { parentIdx, childIdx ->
                val filterList = viewModel.filterData.value
                filterList?.let {
                    val filterItem = filterList[parentIdx]
                    viewModel.updateFilter(filterItem.filterType, filterItem.filterList[childIdx])
                }
            }
        }
        binding?.button?.setOnClickListener { dismiss() }
        binding?.textFieldView?.textField?.doAfterTextChanged { viewModel.nameFilter.postValue(it.toString()) }
    }


}