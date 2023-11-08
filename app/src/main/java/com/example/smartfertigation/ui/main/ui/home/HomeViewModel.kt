package com.example.smartfertigation.ui.main.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.UnstableApi
import com.example.smartfertigation.data.NutrientsRepository
import com.example.smartfertigation.data.ResourceRemote
import com.example.smartfertigation.model.nutrients
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.launch

@UnstableApi class HomeViewModel : ViewModel() {

    private val nutrientsRepository = NutrientsRepository()

    private var homeListLocal= mutableListOf<nutrients>()

    private val _errorMsg : MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _homeList: MutableLiveData<MutableList<nutrients>> = MutableLiveData()
    val homeList : LiveData<MutableList<nutrients>> = _homeList

    fun loadNutrients() {
        viewModelScope.launch {
            val result = nutrientsRepository.loadNutrients()
            result.let {resourceRemote ->
                when (resourceRemote){
                    is ResourceRemote.Success -> {
                        result.data?.documents?.forEach{ document ->
                            val nutrients = document.toObject<nutrients>()
                            nutrients?.let { homeListLocal.add(it) }
                        }
                        _homeList.postValue(homeListLocal)
                    }
                    is ResourceRemote.Error -> {
                        val msg = result.message
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