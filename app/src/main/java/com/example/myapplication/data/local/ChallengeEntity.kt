package com.example.myapplication.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "challenges",
    foreignKeys = [
        ForeignKey(
            entity = AttractionEntity::class,
            parentColumns = ["id"],
            childColumns = ["attractionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("attractionId")]
)
data class ChallengeEntity(
    @PrimaryKey val id: String,
    val attractionId: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false,
    val isDeleted: Boolean = false
)
