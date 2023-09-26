package com.example.composemaps.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.composemaps.presentation.navigation.AppNavigation
import com.example.composemaps.presentation.theme.ComposeGeoMarkerTheme
import com.example.composemaps.presentation.viewmodels.GeoMarkerViewModel
import com.example.composemaps.utils.locationFlow
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch


@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    private val geoMarkerViewModel: GeoMarkerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()
            ComposeGeoMarkerTheme {
                AppNavigation(
                    navController = navController,
                    snackbarHostState = snackbarHostState,
                    geoMarkerViewModel = geoMarkerViewModel,
                    fetchLocationUpdates = ::fetchLocationUpdates
                )
            }
        }
    }

    private fun fetchLocationUpdates() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                fusedLocationClient.locationFlow().collect {
                    it?.let { location ->
                        geoMarkerViewModel.setCurrentLatLng(LatLng(location.latitude, location.longitude))
                    }
                }
            }
        }
    }
}