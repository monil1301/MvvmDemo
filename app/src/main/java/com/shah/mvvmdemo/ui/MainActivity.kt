package com.shah.mvvmdemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.shah.mvvmdemo.R
import com.shah.mvvmdemo.data.UserPreferences
import com.shah.mvvmdemo.ui.auth.AuthActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer { authToken ->
            Toast.makeText(this, authToken , Toast.LENGTH_SHORT).show()
        })
        finish()
        startActivity(Intent(this, AuthActivity::class.java))
    }
}