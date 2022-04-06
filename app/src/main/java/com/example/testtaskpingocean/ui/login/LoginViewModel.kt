package com.example.testtaskpingocean.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskpingocean.data.entity.Error
import com.example.testtaskpingocean.data.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _errorLiveData = MutableLiveData<Error>()
    val errorLiveData: LiveData<Error> get() = _errorLiveData

    private val _navigation = MutableLiveData<Boolean>()
    val navigation: LiveData<Boolean> get() = _navigation

    private val _clickEnable = MutableLiveData<Boolean>()
    val clickEnable: LiveData<Boolean> get() = _clickEnable

    init {
        _clickEnable.postValue(true)
    }

    fun doLogin(email: String, password: String) {
        if (validate(email, password))
            viewModelScope.launch(Dispatchers.Main) {
                val response = repository.doLogin(email, password)
                if (response.isSuccessful) {
                    repository.saveToke(response.body()?.token.orEmpty())
                    if (!repository.getToke().isNullOrEmpty()) {
                        _navigation.postValue(true)
                    } else _errorLiveData.postValue(Error.UNKNOWN)
                } else {
                    _errorLiveData.postValue(Error.NETWORK)
                }
                _clickEnable.postValue(true)
            }
    }

    private fun validate(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            _errorLiveData.postValue(Error.EMAIL)
            return false
        }
        if (password.isEmpty()) {
            _errorLiveData.postValue(Error.PASSWORD)
            return false
        }
        return true
    }

    fun navigationDone() {
        _navigation.postValue(false)
    }

    fun disableClick() {
        _clickEnable.postValue(false)
    }
}