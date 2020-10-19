package com.shah.mvvmdemo.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.shah.mvvmdemo.databinding.FragmentLoginBinding
import com.shah.mvvmdemo.data.network.AuthApi
import com.shah.mvvmdemo.data.network.Resource
import com.shah.mvvmdemo.data.repository.AuthRepository
import com.shah.mvvmdemo.ui.home.HomeActivity
import com.shah.mvvmdemo.ui.base.BaseFragment
import com.shah.mvvmdemo.ui.enabled
import com.shah.mvvmdemo.ui.startActivity
import com.shah.mvvmdemo.ui.visible

class LoginFragment: BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.loginButton.enabled(false)

        fragmentViewModel.loginResponse.observe(viewLifecycleOwner, Observer { loginResponse ->
            when (loginResponse) {
                is Resource.Success -> {
                        binding.loginProgressBar.visible(false)
                        fragmentViewModel.saveAuthToken(loginResponse.value.user.access_token!!)
                        requireActivity().startActivity(HomeActivity::class.java)
                }
                is Resource.Failure -> {
                    binding.loginProgressBar.visible(false)
                    Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.loginPasswordEdittext.addTextChangedListener{ password ->
            val email = binding.loginEmailEdittext.text.toString().trim()
            binding.loginButton.enabled(email.isNotEmpty() && password.toString().isNotEmpty())
        }

        binding.loginButton.setOnClickListener {
            val email = binding.loginEmailEdittext.text.toString().trim()
            val password = binding.loginPasswordEdittext.text.toString().trim()
            binding.loginProgressBar.visible(true)
            fragmentViewModel.login(email, password)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater,container, false)

    override fun getFragmentRepository() = AuthRepository(apiBuilder.buildApi(AuthApi::class.java), userPreferences)
}