package com.example.smartfertigation.ui.newcalculation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.smartfertigation.data.NutrientsRepository
import com.example.smartfertigation.data.ResourceRemote
import com.example.smartfertigation.model.User
import com.example.smartfertigation.model.nutrients
import kotlinx.coroutines.launch

@UnstableApi class NewCalculationViewModel : ViewModel() {

    val nutrientsRepository = NutrientsRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _createNutrientsSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val createNutrientsSuccess: LiveData<Boolean> = _createNutrientsSuccess

    fun validateFields(n_no3: Float, n_nh4: Float, p: Float, k: Float, ca: Float, mg: Float, s: Float) {

        val nutrients = nutrients(n_no3 = n_no3, n_nh4 = n_nh4, p = p, k = k, ca = ca, mg = mg, s = s)
        viewModelScope.launch {
            val result = nutrientsRepository.createNutrients(nutrients)
            result.let { resourceRemote ->
                when (resourceRemote){
                    is ResourceRemote.Success -> {
                        _errorMsg.postValue("Nutrientes guardados con éxito")
                        _createNutrientsSuccess.postValue(true)
                    }
                    is ResourceRemote.Error -> {
                        var msg = result.message
                    }

                    else -> {
                        //don´t use
                    }
                }
            }
        }

    }
}