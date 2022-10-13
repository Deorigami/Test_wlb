package com.ardinata.test.wlb.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.ardinata.test.wlb.core.contract.RouterContract


abstract class BaseViewBindingFragment<VB : ViewBinding> : Fragment(), Binding {

    /**
    * DATA
    * */

    open var binding : VB ? = null
    abstract val layout: Int
    private var rootView: View? = null
    protected var hasInitializedRootView = false
    open var useSaveViewState = false
    open var useDefaultThemeResolver = true

    // Router
    open val router : RouterContract? = null

    /**
    * Lifecycles
    * */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        didMount(view)
        router?.initiate(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    /**
    * METHODS
    * */

    open fun didMount(view: View){
        initBinding(view)
    }


    open fun didMount(view: View, savedInstanceState: Bundle?){
        initBinding(view)
    }

    open fun showLoading(
        isCancelable : Boolean = false,
        onCanceled : (() -> Unit)? = null
    ){
        val loadingDialog = LoadingDialog(onCanceled = onCanceled)
        loadingDialog.isCancelable = isCancelable
        if (!loadingDialog.isVisible) loadingDialog.show(childFragmentManager, "loadingDialog")
    }

    open fun closeLoading(){
        val loadingDialog : LoadingDialog? = try {
            childFragmentManager.findFragmentByTag("loadingDialog") as LoadingDialog
        } catch (e : Exception) {
            null
        }
        loadingDialog?.dismiss()
    }

}