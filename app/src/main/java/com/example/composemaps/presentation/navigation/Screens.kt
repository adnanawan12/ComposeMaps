package com.example.composemaps.presentation.navigation

sealed class Screens(val route: String) {
    object MapScreen : Screens("mapsScreen")
    object GeoMarkerScreen : Screens("geoMarkerScreen")
}