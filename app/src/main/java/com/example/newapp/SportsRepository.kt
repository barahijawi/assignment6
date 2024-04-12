package com.example.newapp

import androidx.lifecycle.LiveData

class SportsRepository(private val sportDao: SportDao) {
    val allSports: LiveData<List<Sport>> = sportDao.getAllSports()

    suspend fun insertSport(sport: Sport) {
        sportDao.insertSport(sport)
    }


}
