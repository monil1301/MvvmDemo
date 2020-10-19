package com.shah.mvvmdemo.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.shah.mvvmdemo.databinding.FragmentLoginBinding
import com.shah.mvvmdemo.data.network.AuthApi
import com.shah.mvvmdemo.data.network.Resource
import com.shah.mvvmdemo.data.repository.AuthRepository
import com.shah.mvvmdemo.ui.base.BaseFragment
import kotlinx.coroutines.launch

class LoginFragment: BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragmentViewModel.loginResponse.observe(viewLifecycleOwner, Observer { loginResponse ->
            when (loginResponse) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        userPreferences.saveAuthToken(loginResponse.value.user.access_token)
                    }
                    Toast.makeText(requireContext(), loginResponse.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.loginButton.setOnClickListener {
            val email = binding.loginEmailEdittext.text.toString().trim()
            val password = binding.loginPasswordEdittext.text.toString().trim()

            fragmentViewModel.login(email, password)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater,container, false)

    override fun getFragmentRepository() = AuthRepository(apiBuilder.buildApi(AuthApi::class.java))
}