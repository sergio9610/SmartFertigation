package com.example.smartfertigation.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.UnstableApi
import com.example.smartfertigation.data.ResourceRemote
import com.example.smartfertigation.data.UserRepository
import emailValidator
import kotlinx.coroutines.launch


@UnstableApi class RegisterViewModel:ViewModel() {

    private val userRepository = UserRepository()

    private val _errorMsg : MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _registerSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val registerSuccess: LiveData<Boolean> = _registerSuccess
    fun validateFields(name: String, email: String, password: String, repeatPassword: String, cel: String, company: String, city: String): Boolean {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty() || cel.isEmpty() || company.isEmpty() || city.isEmpty()) {
            _errorMsg.value = "Debe digitar todos los campos"
            return false
        } else {
            if (password != repeatPassword) {
                _errorMsg.value = "Las contraseñas deben ser iguales"
            } else {
                if (password.length < 6) {
                    _errorMsg.value = "La contraseña debe tener minimo 6 caracteres"
                } else {
                    if (!emailValidator(email)){
                        _errorMsg.value = "El correo electrónico es inválido"
                    } else {
                        viewModelScope.launch {
                            val result = userRepository.registerUser(email, password)
                            result.let { resourceRemote ->
                                when (resourceRemote){
                                    is ResourceRemote.Success -> {
                                        _registerSuccess.postValue(true)
                                        _errorMsg.postValue("Usuario creado exitosamente")
                                    }
                                    is ResourceRemote.Error -> {
                                        var msg = result.message
                                        when (msg){
                                            "The email address is already in use by another account." -> msg = "Ya existe una cuenta con ese correo electrónico"
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
            }
        }
        return true
    }
}