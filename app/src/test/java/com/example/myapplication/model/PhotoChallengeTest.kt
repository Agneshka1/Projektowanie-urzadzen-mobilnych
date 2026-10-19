package com.example.myapplication.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PhotoChallengeTest {

    @Test
    fun `photo challenge with valid data and photoUri is valid`() {
        val challenge = PhotoChallenge(
            id = "1",
            attractionId = "attr_101",
            photoUri = "content://media/external/images/1"
        )

        assertTrue(challenge.isValid())
    }

    @Test
    fun `photo challenge without photoUri is invalid`() {
        val challenge = PhotoChallenge(
            id = "1",
            attractionId = "attr_101",
            photoUri = null
        )

        assertFalse(challenge.isValid())
    }

    @Test
    fun `soft deleted photo challenge is invalid`() {
        val challenge = PhotoChallenge(
            id = "1",
            attractionId = "attr_101",
            photoUri = "content://media/external/images/1",
            isDeleted = true
        )

        assertFalse(challenge.isValid())
    }

    @Test
    fun `execute marks valid photo challenge as completed`() {
        val challenge = PhotoChallenge(
            id = "1",
            attractionId = "attr_101",
            photoUri = "content://media/external/images/1"
        )

        assertFalse(challenge.isCompleted)
        challenge.execute()
        assertTrue(challenge.isCompleted)
    }

    @Test
    fun `polymorphic execution via ChallengeAction interface`() {
        val action: ChallengeAction = PhotoChallenge(
            id = "2",
            attractionId = "attr_102",
            photoUri = "content://media/external/images/2"
        )

        action.execute()
        val challenge = action as BaseChallenge
        assertTrue(challenge.isCompleted)
    }
}
