package com.example.smartfertigation.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import emailValidator


class RegisterViewModel:ViewModel() {


//    private val userRepository = UserRepository()

    private val _errorMsg : MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMsg
    fun validateFields(name: String, email: String, password: String, repeatPasswor: String, cel: String, empresa: String, ciudad: String): Boolean {
        if( name.isEmpty() || email.isEmpty() || password.isEmpty() || repeatPasswor.isEmpty() || cel.isEmpty() || empresa.isEmpty() || ciudad.isEmpty()){
            _errorMsg.value = "Debe digitar todos los campos"
            return false
        } else{
            if(password != repeatPasswor){
                _errorMsg.value = "Las contraseñas deben ser iguales"
                return false
            }else{
                if(password.length < 6){
                    _errorMsg.value = "La contraseña debe tener minimo 6 digitos"
                    return false
                }else{
                    if(!emailValidator(email)){
                        _errorMsg.value = "El correo electronico esta mal escrito"
                        return false
                    }else{
                        if (cel.length < 10){
                            _errorMsg.value = "El número de celular debe tener minimo 10 digitos"
                        }
                        return true
                    }
                }
            }
        }
    }
}