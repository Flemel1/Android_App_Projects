package com.example.videoplayer

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MainActivity : AppCompatActivity(), Player.EventListener {
    private lateinit var progressBar: ProgressBar
    private lateinit var exoplayerView: PlayerView

    private val TAG = "msg"
    private val KEY = "playPosition"
    private lateinit var simpleExoplayer: SimpleExoPlayer
    private var playbackPosition: Long = 0

    //use this variable in function initializePlayer to play video on internet
    private val videoUrl = "https://html5demos.com/assets/dizzy.mp4"
    //use this variable in function initializePlayer to play video on device
    private val videoPath = "/storage/0403-0201/video/UCDownloads/SnS-S5-13 END -480p-SAMEHADAKU.VIP.mkv"

    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(this, "exoplayer-sample")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState?.let {
            playbackPosition = savedInstanceState!!.getLong(KEY)
            Log.i("not null", playbackPosition.toString())
            exoplayerView = findViewById(R.id.exoplayerView)
            progressBar = findViewById(R.id.progressBar)
            initializePlayer(playbackPosition)
        } ?: run {
            exoplayerView = findViewById(R.id.exoplayerView)
            progressBar = findViewById(R.id.progressBar)
            Log.i("null", playbackPosition.toString())
            onRequestMultiplePermissions()
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun onRequestMultiplePermissions() {
        Dexter.withContext(this)
            .withPermissions(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report!!.areAllPermissionsGranted())
                    {
                        Log.i(TAG, "all request os granted")
                        initializePlayer(playbackPosition)
                    }
                    if (report!!.isAnyPermissionPermanentlyDenied)
                    {
                        Log.i(TAG, "one or more request is permanently denied")
                    }
                }

                override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?,
                                                                token: PermissionToken?) {
                    Log.i(TAG, "request multiple permissions")
                    token?.continuePermissionRequest()
                }

            }).onSameThread().check()
    }

    private fun initializePlayer(position: Long) {
        simpleExoplayer = SimpleExoPlayer.Builder(this).build()
        preparePlayer(videoUrl)
        exoplayerView.player = simpleExoplayer
        simpleExoplayer.seekTo(position)
        simpleExoplayer.playWhenReady = true
        simpleExoplayer.addListener(this)
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        //create media from uri
        return ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri)
    }

    private fun preparePlayer(videoUrl: String) {
        val uri = Uri.parse(videoUrl)
        val mediaSource = buildMediaSource(uri)
        simpleExoplayer.prepare(mediaSource)
    }

    private fun releasePlayer() {
        playbackPosition = simpleExoplayer.currentPosition
        Log.i("releasePlayer", simpleExoplayer.currentPosition.toString())
        simpleExoplayer.release()
    }


    override fun onPlayerError(error: ExoPlaybackException) {
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playbackState == Player.STATE_BUFFERING)
            progressBar.visibility = View.VISIBLE
        else if (playbackState == Player.STATE_READY || playbackState == Player.STATE_ENDED)
            progressBar.visibility = View.INVISIBLE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("onSaveInstanceState", "onSvaedInstance")
        outState.putLong(KEY, simpleExoplayer.currentPosition)
        super.onSaveInstanceState(outState)
    }
}