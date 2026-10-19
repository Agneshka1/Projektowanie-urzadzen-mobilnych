package com.example.myapplication.model

class PhotoChallenge(
    override val id: String,
    override val attractionId: String,
    override val timestamp: Long = System.currentTimeMillis(),
    override var isCompleted: Boolean = false,
    override var isDeleted: Boolean = false,
    val photoUri: String? = null
) : BaseChallenge(id, attractionId, timestamp, isCompleted, isDeleted) {

    override fun isValid(): Boolean {
        return super.isValid() && !photoUri.isNullOrBlank()
    }

    override fun execute() {
        if (isValid()) {
            isCompleted = true
        }
    }
}
