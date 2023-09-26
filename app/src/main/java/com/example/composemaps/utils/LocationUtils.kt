package com.example.composemaps.utils

import android.annotation.SuppressLint
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

@SuppressLint("MissingPermission")
fun FusedLocationProviderClient.locationFlow() = callbackFlow {
    val callback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            try {
                trySend(result.lastLocation)
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }
    requestLocationUpdates(createLocationRequest(), callback, Looper.getMainLooper())
        .addOnFailureListener { e ->
            close(e)
        }

    awaitClose {
        removeLocationUpdates(callback)
    }
}

fun createLocationRequest(): LocationRequest {
    return LocationRequest.create().apply {
        interval = 20000
        fastestInterval = 10000
        priority = Priority.PRIORITY_HIGH_ACCURACY
    }

}