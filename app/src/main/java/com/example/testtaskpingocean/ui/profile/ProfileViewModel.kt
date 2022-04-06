package com.example.testtaskpingocean.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskpingocean.data.entity.UserProfile
import com.example.testtaskpingocean.data.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: LoginRepository) : ViewModel() {
    private val _profileLiveData = MutableLiveData<UserProfile>()
    val profileLiveData: LiveData<UserProfile> get() = _profileLiveData

    fun getProfile() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = repository.getProfile()
            if (response.isSuccessful) {
                val profile = response.body()
                profile?.let {
                    _profileLiveData.postValue(it)
                }
            }
        }
    }
}