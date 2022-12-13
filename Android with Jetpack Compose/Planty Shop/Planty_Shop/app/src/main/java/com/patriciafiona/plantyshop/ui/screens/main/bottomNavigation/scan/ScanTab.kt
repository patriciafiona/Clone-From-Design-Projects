package com.patriciafiona.plantyshop.ui.screens.main.bottomNavigation.scan

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.patriciafiona.plantyshop.R
import com.patriciafiona.plantyshop.ui.widgets.CameraOpen
import java.io.File

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanTab(
    navController: NavController,
    bottomNavController: NavController,
    getDirectory: File
){
    val cameraPermissionState = rememberPermissionState(
        Manifest.permission.CAMERA
    )

    if (cameraPermissionState.hasPermission) {
        CameraOpen(getDirectory, bottomNavController = bottomNavController)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    cameraPermissionState.launchPermissionRequest()
                }
            ) {
                Text(text = "Camera Permission")
            }
        }
    }

}