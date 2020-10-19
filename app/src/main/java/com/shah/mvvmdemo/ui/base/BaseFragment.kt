package com.shah.mvvmdemo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.shah.mvvmdemo.data.UserPreferences
import com.shah.mvvmdemo.data.network.ApiBuilder
import com.shah.mvvmdemo.data.network.UserApi
import com.shah.mvvmdemo.data.repository.BaseRepository
import com.shah.mvvmdemo.ui.auth.AuthActivity
import com.shah.mvvmdemo.ui.startActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<viewModal: BaseViewModel, viewBinding: ViewBinding, repository: BaseRepository>: Fragment() {

    protected lateinit var userPreferences: UserPreferences
    protected lateinit var authToken: String
    protected lateinit var binding: viewBinding
    protected lateinit var fragmentViewModel: viewModal
    protected val apiBuilder = ApiBuilder()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        fragmentViewModel = ViewModelProvider(this,factory).get(getViewModel())
        lifecycleScope.launch {
            userPreferences.authToken.first().toString()
        }
        return binding.root
    }

    fun logout() = lifecycleScope.launch {
        val authToken = userPreferences.authToken.first()
        val api = apiBuilder.buildApi(UserApi::class.java,authToken)
        fragmentViewModel.logout(api)
        userPreferences.clear()
        requireActivity().startActivity(AuthActivity::class.java)
    }

    abstract fun getViewModel() : Class<viewModal>

    abstract fun getFragmentBinding(inflater: LayoutInflater,container: ViewGroup?) : viewBinding

    abstract fun getFragmentRepository() : repository
}