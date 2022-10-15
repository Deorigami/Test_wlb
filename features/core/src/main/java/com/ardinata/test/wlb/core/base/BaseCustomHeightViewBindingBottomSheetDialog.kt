package com.ardinata.test.wlb.core.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.ardinata.core.R
import com.ardinata.test.wlb.core.contract.RouterContract
import com.ardinata.test.wlb.core.modal.GeneralAlertHalfModal
import com.ardinata.test.wlb.util.convertDpToPx
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseCustomHeightViewBindingBottomSheetDialog<VB : ViewBinding> : BottomSheetDialogFragment(), Binding {

    open var binding: VB? = null

    // --------------------------------------
    // layout
    // --------------------------------------
    abstract val layout: Int

    // --------------------------------------
    // router
    // --------------------------------------
    open val router: RouterContract? = null
    open var isCancellableMode: Boolean? = null
    open var isUseDefaultThemeWrapper: Boolean = true
    open var bottomSheetMode : BottomSheetMode = BottomSheetMode.CUSTOM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (isUseDefaultThemeWrapper) {
            val styleTheme = activity?.theme
            val contextThemeWrapper = ContextThemeWrapper(activity, styleTheme)
            val localInflater = inflater.cloneInContext(contextThemeWrapper)
            localInflater.inflate(layout, container, false)
        } else {
            inflater.inflate(layout, container, false)
        }
    }

    override fun onStart() {
        super.onStart()
        if (bottomSheetMode == BottomSheetMode.ALMOST_FULL){
            val dialog = dialog
            if (dialog != null) {
                dialog.window?.apply {
                    val lp = WindowManager.LayoutParams()
                    lp.copyFrom(attributes)
                    lp.width = LinearLayout.LayoutParams.MATCH_PARENT
                    lp.height = (resources.displayMetrics.heightPixels) - (50).convertDpToPx(requireContext()).toInt()
                    attributes = lp
                    setGravity(Gravity.BOTTOM)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        didMount(view)
        isCancelable = isCancellableMode ?: false
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
//    }

    override fun getTheme(): Int = R.style.CustomBottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (bottomSheetMode == BottomSheetMode.FULL || bottomSheetMode == BottomSheetMode.ALMOST_FULL) {
            val dialog = object : BottomSheetDialog(requireContext()) {
                override fun onBackPressed() {
                    super.onBackPressed()
                    dismiss()
                }
            }
            dialog.setOnShowListener {
                val bottomSheetDialog = it as BottomSheetDialog
                val parentLayout = bottomSheetDialog.findViewById<View>(
                    com.google.android.material.R.id.design_bottom_sheet
                )
                parentLayout?.let { bottomSheet ->
                    if (bottomSheetMode == BottomSheetMode.ALMOST_FULL) bottomSheet.background = ColorDrawable(Color.TRANSPARENT)
                    val behaviour = BottomSheetBehavior.from(bottomSheet)
                    val layoutParams = bottomSheet.layoutParams
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
                    bottomSheet.layoutParams = layoutParams
                    behaviour.state = BottomSheetBehavior.STATE_EXPANDED
                    behaviour.isDraggable = false
                }
            }
            return dialog
        } else return super.onCreateDialog(savedInstanceState)
    }


    // --------------------------------------------------------------------------------
    // LIFE_CYCLES
    // --------------------------------------------------------------------------------

    open fun didMount(view: View) {
        initBinding(view)
        binding?.bindView()
    }

    open fun VB.bindView() {}

    open fun closeLoading(){
        val loadingDialog : LoadingDialog? = try {
            childFragmentManager.findFragmentByTag("loadingDialog") as LoadingDialog
        } catch (e : Exception) {
            null
        }
        loadingDialog?.dismiss()
    }

    open fun showHalfModal(
        fragmentManager: FragmentManager,
        title : String? = null,
        body : String? = null,
        primaryButtonTitle : String? = null,
        onPrimaryButtonPressed : (() -> Unit) ? = null,
        secondaryButtonTitle : String? = null,
        onSecondaryButtonPressed : (() -> Unit) ? = null,
        isDismissable : Boolean = false,
        onDialogDismissed : (() -> Unit)? = null,
        imageResource : Any? = null
    ){
        val halfModal = GeneralAlertHalfModal(
            title?:"",
            body?:"",
            imageResource,
            primaryButtonTitle?:"",
            onPrimaryButtonPressed,
            secondaryButtonTitle ?: "",
            onSecondaryButtonPressed,
            isDismissable = isDismissable
        )

        lifecycleScope.launchWhenResumed { halfModal.show(fragmentManager,"") }
    }

    open fun showLoading(
        isCancelable : Boolean = false,
        onCanceled : (() -> Unit)? = null
    ){
        val loadingDialog = LoadingDialog(onCanceled = onCanceled)
        loadingDialog.isCancelable = isCancelable
        loadingDialog.show(childFragmentManager, "loadingDialog")

    }

    companion object {
        enum class BottomSheetMode{
            FULL,
            ALMOST_FULL,
            CUSTOM
        }
    }

}