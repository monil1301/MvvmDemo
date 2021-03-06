package com.shah.mvvmdemo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.shah.mvvmdemo.data.network.Resource
import com.shah.mvvmdemo.data.network.UserApi
import com.shah.mvvmdemo.data.repository.UserRepository
import com.shah.mvvmdemo.data.response.User
import com.shah.mvvmdemo.databinding.FragmentHomeBinding
import com.shah.mvvmdemo.ui.base.BaseFragment
import com.shah.mvvmdemo.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)

        fragmentViewModel.getUser()
        fragmentViewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    updateUI(it.value.user)
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
            }
        })

        binding.buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun updateUI(user: User) {
        with(binding) {
            textViewId.text = user.id.toString()
            textViewName.text = user.name
            textViewEmail.text = user.email
        }
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = apiBuilder.buildApi(UserApi::class.java,token)
        return UserRepository(api)
    }

}