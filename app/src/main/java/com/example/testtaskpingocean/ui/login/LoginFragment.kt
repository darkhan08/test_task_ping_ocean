package com.example.testtaskpingocean.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.testtaskpingocean.R
import com.example.testtaskpingocean.data.entity.Error
import com.example.testtaskpingocean.databinding.FragmentLoginBinding
import com.example.testtaskpingocean.ui.profile.ProfileFragment
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    private var email = ""
    private var password = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        setUpView()
        setUpViewModel()
        return binding.root
    }

    private fun setUpViewModel() {
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorLiveDataObserved)
        viewModel.navigation.observe(viewLifecycleOwner, navigationLiveDataObserved)
        viewModel.clickEnable.observe(viewLifecycleOwner, clickEnableLiveDataObserved)
    }

    private val clickEnableLiveDataObserved = Observer<Boolean>{ enable ->
        binding.enterBtn.setOnClickListener {
            if(enable){
                viewModel.doLogin(email, password)
                viewModel.disableClick()
            }
        }
    }

    private val errorLiveDataObserved = Observer<Error> {
        val message = when (it) {
            Error.NETWORK -> "Network Error"
            Error.EMAIL -> "Incorrect email"
            Error.PASSWORD -> "Incorrect password"
            else -> "something went wrong"
        }
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private val navigationLiveDataObserved = Observer<Boolean> {
        if (it) {
            parentFragmentManager.beginTransaction().replace(R.id.fragment, ProfileFragment())
                .addToBackStack("root")
                .commit()

            viewModel.navigationDone()
        }
    }

    private fun setUpView() = binding.run {
        email = loginEmail.text.toString()
        password = loginPassword.text.toString()
    }
}