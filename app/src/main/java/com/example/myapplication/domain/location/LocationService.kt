package com.example.myapplication.domain.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationService {
    fun getLocationUpdates(): Flow<Location?>
    suspend fun getLastKnownLocation(): Location?
}
