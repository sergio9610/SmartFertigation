package com.example.smartfertigation.ui.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.UnstableApi
import com.example.smartfertigation.data.ResourceRemote
import com.example.smartfertigation.data.UserRepository
import kotlinx.coroutines.launch

@UnstableApi class MainViewModel : ViewModel(){

    private val userRepository = UserRepository()

    private val _errorMsg : MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _userLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    val userLoggedIn: LiveData<Boolean> = _userLoggedIn
    fun verifyUser() {
        viewModelScope.launch {
            val result = userRepository.verifyUser()
            result.let { resourceRemote ->
                when (resourceRemote){
                    is ResourceRemote.Success -> {
                        if (result.data == false)
                        _userLoggedIn.postValue(true)
                    }
                    is ResourceRemote.Error -> {
                        var msg = result.message
                        when (msg){
                            "A network error (such as timeout, interrupted connection or unreachable host) has ocurred." -> msg = "Revise su conexión a internet"

                        }
                        _errorMsg.postValue(msg)
                    }

                    else -> {
                        //don´t use
                    }
                }
            }
        }
    }

}