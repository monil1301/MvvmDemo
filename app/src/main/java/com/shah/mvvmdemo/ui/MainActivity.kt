package com.shah.mvvmdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.shah.mvvmdemo.R
import com.shah.mvvmdemo.data.UserPreferences
import com.shah.mvvmdemo.ui.auth.AuthActivity
import com.shah.mvvmdemo.ui.home.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer { authToken ->
            val activity = if (authToken != null) HomeActivity::class.java else AuthActivity::class.java
            startActivity(activity)
        })
    }
}