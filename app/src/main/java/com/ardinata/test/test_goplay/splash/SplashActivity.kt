package com.ardinata.test.test_goplay.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ardinata.test.test_goplay.R
import com.ardinata.test.test_goplay.databinding.ActivityMainBinding
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