package com.example.testtaskpingocean.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.testtaskpingocean.databinding.FragmentProfileBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModel()

    private val factory = ProfileFactory()
    private val adapter: ProfileListAdapter by lazy {
        ProfileListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel.getProfile()
        setUpViewModel()
        setUpView()

        return binding.root
    }

    private fun setUpViewModel() {
        viewModel.profileLiveData.observe(viewLifecycleOwner, Observer {
            val list = factory.create(it)
            adapter.submitList(list = list)
        })
    }


    private fun setUpView() = binding.run {
        rvUserInfo.adapter = adapter
        buttonExit.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}