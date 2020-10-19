package com.shah.mvvmdemo.ui

import android.app.Activity
import android.content.Intent
import android.view.View

fun<A: Activity> Activity.startActivity(activity: Class<A>) {
    val intent = Intent(this,activity).also {
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enabled(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}