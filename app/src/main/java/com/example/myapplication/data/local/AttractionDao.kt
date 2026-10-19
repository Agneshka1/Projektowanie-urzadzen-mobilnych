package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AttractionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttraction(attraction: AttractionEntity)

    @Query("SELECT * FROM attractions WHERE id = :id")
    suspend fun getAttractionById(id: String): AttractionEntity?

    @Query("SELECT * FROM attractions")
    fun getAllAttractions(): Flow<List<AttractionEntity>>
}
