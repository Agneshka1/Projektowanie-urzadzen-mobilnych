package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ChallengeDao {

    // --- Create ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallenge(challenge: ChallengeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallenges(challenges: List<ChallengeEntity>)

    // --- Read ---
    @Query("SELECT * FROM challenges WHERE id = :id")
    suspend fun getChallengeById(id: String): ChallengeEntity?

    @Query("SELECT * FROM challenges WHERE isDeleted = 0")
    fun getAllActiveChallenges(): Flow<List<ChallengeEntity>>

    @Query("SELECT * FROM challenges WHERE attractionId = :attractionId AND isDeleted = 0")
    fun getActiveChallengesForAttraction(attractionId: String): Flow<List<ChallengeEntity>>

    @Query("SELECT * FROM challenges")
    fun getAllChallengesIncludingDeleted(): Flow<List<ChallengeEntity>>

    // --- Update ---
    @Update
    suspend fun updateChallenge(challenge: ChallengeEntity)

    // --- Soft Delete ---
    @Query("UPDATE challenges SET isDeleted = 1 WHERE id = :id")
    suspend fun softDeleteChallenge(id: String)

    // --- Hard Delete ---
    @Delete
    suspend fun hardDeleteChallenge(challenge: ChallengeEntity)

    @Query("DELETE FROM challenges WHERE id = :id")
    suspend fun hardDeleteChallengeById(id: String)
}
