package com.example.composemaps.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composemaps.presentation.screens.GeoMarkerScreen
import com.example.composemaps.presentation.screens.MapsScreen
import com.example.composemaps.presentation.viewmodels.GeoMarkerViewModel

@ExperimentalMaterial3Api
@Composable
fun AppNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    geoMarkerViewModel: GeoMarkerViewModel,
    fetchLocationUpdates: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screens.MapScreen.route
    ) {
        composable(Screens.MapScreen.route) {
            MapsScreen(
                snackbarHostState = snackbarHostState,
                navController = navController,
                fetchLocationUpdates = fetchLocationUpdates
            )
        }
        composable(Screens.GeoMarkerScreen.route) {
            GeoMarkerScreen(geoMarkerViewModel)
        }
    }
}