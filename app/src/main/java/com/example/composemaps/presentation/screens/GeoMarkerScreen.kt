package com.example.composemaps.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.composemaps.presentation.composeables.GeoMarkerButton
import com.example.composemaps.presentation.composeables.GeoMarkerTopBar
import com.example.composemaps.presentation.viewmodels.GeoMarkerViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@ExperimentalMaterial3Api
@Composable
fun GeoMarkerScreen(
    geoMarkerViewModel: GeoMarkerViewModel
) {
    val context = LocalContext.current
    var areaPoints = mutableListOf<LatLng>()
    var drawPolygon by remember {
        mutableStateOf(false)
    }

    val currentLocation by geoMarkerViewModel.currentLatLng.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 16f)
    }

    var showSavePoint by remember {
        mutableStateOf(false)
    }

    var clickedLocation by remember {
        mutableStateOf(LatLng(0.0, 0.0))
    }

    Scaffold(
        topBar = { GeoMarkerTopBar() },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    onMapClick = {
                        // TODO Add click listener
                    }
                ) {
                    // TODO Add Polygon
                }

                // TODO Save Point UI

                GeoMarkerButton(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, bottom = 16.dp)
                        .align(Alignment.BottomCenter),
                    drawPolygon = drawPolygon,
                    areaPoints = areaPoints
                ) { drawPolygonCallback ->
                    drawPolygon = drawPolygonCallback
                    if (!drawPolygonCallback) {
                        areaPoints = mutableListOf()
                    }
                }
            }
        }
    )


}