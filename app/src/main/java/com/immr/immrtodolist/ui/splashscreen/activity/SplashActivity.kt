package com.immr.immrtodolist.ui.splashscreen.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.immr.immrtodolist.core.BaseActivity
import com.immr.immrtodolist.databinding.ActivitySplashBinding
import com.immr.immrtodolist.ui.home.activity.ListNoteActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ListNoteActivity::class.java)
            startActivity(intent)
        }, 3000)
    }

}