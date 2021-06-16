package com.techdeity.videoplayerinternet

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var videoplayer:SimpleExoPlayer?=null
    private var sampleUrl="https://video.twimg.com/ext_tw_video/1339135927185108992/pu/vid/1280x720/wqfM3d61vDRQuK1u.mp4?tag=10"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializePlayer()


    }
    private fun buildMediaSource():MediaSource {

        val dataSourceFactory =DefaultDataSourceFactory(this,"sample")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(sampleUrl))

    }

    private fun initializePlayer() {
        videoplayer=SimpleExoPlayer.Builder(this).build()
        video_player_view?.player=videoplayer
        buildMediaSource()?.let{
            videoplayer?.prepare(it)
        }

    }

    override fun onResume() {
        super.onResume()
        videoplayer?.playWhenReady=true
    }

    override fun onStop() {
        super.onStop()
        videoplayer?.playWhenReady=false
        if(isFinishing){
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        videoplayer?.release()
    }

}