package com.example.newapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SportsViewModel(private val sportsRepository: SportsRepository) : ViewModel() {
    val sports: LiveData<List<Sport>> = sportsRepository.allSports


    fun addNewSport(type: String, name: String, instructions: String) {
        viewModelScope.launch {
            val newSport = Sport( type = type,  name =  name, instructions = instructions)
            sportsRepository.insertSport(newSport)
        }
    }

    class Factory(private val sportsRepository: SportsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SportsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SportsViewModel(sportsRepository) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }
}
