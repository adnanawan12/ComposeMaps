package com.example.composemaps.data

import com.google.android.gms.maps.model.LatLng

data class GeoPoints(
    val point: LatLng,
    val hideSavePointUi: Boolean = false
)