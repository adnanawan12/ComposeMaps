package com.example.composemaps.permissions

sealed class PermissionAction {
    object PermissionGranted : PermissionAction()
    object PermissionDenied : PermissionAction()
}