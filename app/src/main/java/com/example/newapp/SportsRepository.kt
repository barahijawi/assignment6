package com.example.newapp

class SportsRepository(private val sportDao: SportDao) {

    suspend fun insertSport(sport: Sport) {
        sportDao.insertSport(sport)
    }
}
