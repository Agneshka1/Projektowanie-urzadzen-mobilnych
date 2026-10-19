package com.example.myapplication.model

abstract class BaseChallenge(
    open val id: String,
    open val attractionId: String,
    open val timestamp: Long = System.currentTimeMillis(),
    open var isCompleted: Boolean = false,
    open var isDeleted: Boolean = false
) : Validatable, ChallengeAction {

    override fun isValid(): Boolean {
        return id.isNotBlank() && attractionId.isNotBlank() && !isDeleted
    }
}
