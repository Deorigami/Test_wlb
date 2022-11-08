package com.ardinata.feature_dashboard.detail

import android.os.Bundle
import com.ardinata.feature_dashboard.R
import com.ardinata.test.test_goplay.core.base.BaseActivity
import com.ardinata.util.databinding.BaseActivityLayoutBinding

class DetailActivity(
    layout: Int = R.layout.base_activity_layout
) : BaseActivity(layout){
    private val binding by lazy { BaseActivityLayoutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fragment = DetailPage()
        fragment.arguments = intent.extras
        supportFragmentManager.beginTransaction().replace(binding.fragmentHolder.id, fragment).commit()
    }
}