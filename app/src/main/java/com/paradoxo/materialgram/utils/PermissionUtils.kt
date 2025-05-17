package com.paradoxo.materialgram.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.core.content.ContextCompat
import kotlin.apply
import kotlin.collections.all
import kotlin.collections.toTypedArray

class PermissionUtils(
    private val context: Context
) {

    fun microphonePermissionsGranted() = MICROPHONE_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }


    companion object {
        val MICROPHONE_PERMISSIONS = arrayOf(Manifest.permission.RECORD_AUDIO)
    }
}