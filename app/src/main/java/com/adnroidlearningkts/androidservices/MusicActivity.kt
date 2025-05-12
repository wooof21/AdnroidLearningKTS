package com.adnroidlearningkts.androidservices

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R

class MusicActivity : AppCompatActivity() {

    private lateinit var play: Button
    private lateinit var stop: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_music)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        play = findViewById(R.id.music_service_play)
        stop = findViewById(R.id.music_service_stop)

        //define the service
        val musicService = Intent(applicationContext, MusicService::class.java)

        play.setOnClickListener {

            startService(musicService)
        }

        stop.setOnClickListener {
            stopService(musicService)
        }
    }
}