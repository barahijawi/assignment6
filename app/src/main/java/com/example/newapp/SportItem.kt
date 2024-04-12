package com.example.newapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "sports")
data class Sport(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,
    val name: String,
    val instructions: String
)


@Dao
interface SportDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSport(sport: Sport)

    @Query("SELECT * FROM sports ORDER BY id DESC")
    fun getAllSports(): LiveData<List<Sport>>
}