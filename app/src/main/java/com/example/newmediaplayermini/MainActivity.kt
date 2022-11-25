package com.example.newmediaplayermini

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar

import kotlin.system.measureNanoTime

class MainActivity : AppCompatActivity() {
    lateinit var runnable: Runnable
    private var handler = Handler()
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.high_sky)
        val play_btn: ImageButton = findViewById(R.id.play_btn)
        val seekBar: SeekBar = findViewById(R.id.seekbar)


        seekBar.progress = 0
        seekBar.max = mediaPlayer.duration


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, changed: Boolean) {
                if (changed) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }
        })

        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    mediaPlayer.setOnCompletionListener {
        play_btn.setImageResource(R.drawable.ic_play)
        seekBar.progress = 0
    }

        play_btn.setOnClickListener{
            if(!mediaPlayer.isPlaying){
                mediaPlayer.start()
                play_btn.setImageResource(R.drawable.ic_pause)
            } else {
                mediaPlayer.pause()
                play_btn.setImageResource(R.drawable.ic_play)
            }
        }


    }
}