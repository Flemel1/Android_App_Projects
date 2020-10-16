package com.example.contentproviderexample

import android.content.ContentProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import com.example.contentproviderexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val TAG = "information"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        getCursorOfVideo()
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
}