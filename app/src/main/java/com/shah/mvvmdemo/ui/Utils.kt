package com.shah.mvvmdemo.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.shah.mvvmdemo.data.network.Resource
import com.shah.mvvmdemo.ui.auth.LoginFragment
import com.shah.mvvmdemo.ui.base.BaseFragment

fun <A : Activity> Activity.startActivity(activity: Class<A>) {
    Intent(this, activity).also {
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

fun View.snackBar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        it()
    }
    snackbar.show()
}

fun Fragment.handleApiErrors(failure: Resource.Failure, retry: (() -> Unit)? = null) {
    when {
        failure.isNetworkError -> requireView().snackBar(
            "Please check your internet connection",
            retry
        )
        failure.errorCode == 401 -> {
            if (this is LoginFragment) {
                requireView().snackBar("You've entered incorrect email or password")
            } else {
                (this as BaseFragment<*, *, *>).logout()
            }
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackBar(error)
        }
    }
}

fun convertDpToPx(context: Context, dp: Float): Float {
    return dp * context.resources.displayMetrics.density
}