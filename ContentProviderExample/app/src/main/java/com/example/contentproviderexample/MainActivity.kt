package com.example.contentproviderexample

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.contentproviderexample.databinding.ActivityMainBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val TAG = "information"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        requestPermissions()
    }

    private fun requestPermissions() {
        Dexter.withContext(this)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response : PermissionGrantedResponse?) {
                    Log.i(TAG, "Permission Granted")
                    getCursorOfVideo()
                }

                override fun onPermissionDenied(response : PermissionDeniedResponse?) {
                    Log.i(TAG, "Permission Denied")
                    if (response?.isPermanentlyDenied!!) {
                        Log.i(TAG, "Permission Permanent Denied")
                        openSettingPermission()
                    }
                    finish()
                }

                override fun onPermissionRationaleShouldBeShown(
                    respone : PermissionRequest?,
                    token : PermissionToken?
                ) {
                    Log.i(TAG, "This app need permission for read memory card")
                    token?.continuePermissionRequest()
                }

            }).check()
    }

    private fun openSettingPermission() {
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).let {
            val uri = Uri.fromParts("package", packageName, null)
            it.data = uri
            startActivityForResult(it, 101)
        }
    }

    private fun getCursorOfVideo() {
        var projection : Array<String> = arrayOf(
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATA
        )

        var mCursor = contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        when (mCursor?.count) {
            null -> {
                Log.i(TAG, "cursor is null")
            }
            0 -> {
                Log.i(TAG, "cursor not null but item in cursor is 0")
            }
            else -> {
                while (mCursor?.moveToNext()) {
                    Log.i(TAG, mCursor?.getString(0))
                    Log.i(TAG, mCursor?.getString(1))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requestPermissions()
    }
}