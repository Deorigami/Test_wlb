package com.ardinata.test.wlb.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ardinata.test.wlb.R
import com.ardinata.test.wlb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.main_nav, SplashPage()).commit()
    }
}