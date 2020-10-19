package com.shah.mvvmdemo.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.shah.mvvmdemo.data.network.AuthApi
import com.shah.mvvmdemo.data.network.Resource
import com.shah.mvvmdemo.data.repository.AuthRepository
import com.shah.mvvmdemo.databinding.FragmentRegisterBinding
import com.shah.mvvmdemo.ui.base.BaseFragment
import com.shah.mvvmdemo.ui.enabled
import com.shah.mvvmdemo.ui.handleApiErrors
import com.shah.mvvmdemo.ui.visible

class RegisterFragment : BaseFragment<AuthViewModel, FragmentRegisterBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.registerButton.enabled(false)

        fragmentViewModel.signupResponse.observe(viewLifecycleOwner, Observer { signupResponse ->
            binding.registerProgressBar.visible(signupResponse is Resource.Loading)
            when (signupResponse) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), signupResponse.value.message, Toast.LENGTH_SHORT).show()
                    val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                    Navigation.findNavController(requireView()).navigate(action)
                }
                is Resource.Failure -> handleApiErrors(signupResponse) { signup() }
            }
        })

        binding.haveAccountTextView.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.registerPasswordEdittext.addTextChangedListener { password ->
            val email = binding.registerEmailEdittext.text.toString().trim()
            val name = binding.registerUsernameEdittext.text.toString().trim()
            binding.registerButton.enabled(
                name.isNotEmpty() && email.isNotEmpty() && password.toString().isNotEmpty()
            )
        }

        binding.registerButton.setOnClickListener {
            signup()
        }
    }

    private fun signup() {
        val name = binding.registerUsernameEdittext.text.toString().trim()
        val email = binding.registerEmailEdittext.text.toString().trim()
        val password = binding.registerPasswordEdittext.text.toString().trim()
        fragmentViewModel.signup(name, email, password, password)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(apiBuilder.buildApi(AuthApi::class.java), userPreferences)
}