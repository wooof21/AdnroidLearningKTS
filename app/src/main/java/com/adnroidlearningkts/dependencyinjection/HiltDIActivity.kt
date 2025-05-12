package com.adnroidlearningkts.dependencyinjection

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @AndroidEntryPoint: marks Android components(Activities, Fragments ...) as injection targets
 * It allows Hilt to provide dependencies directly into these components
 */
@AndroidEntryPoint
class HiltDIActivity : AppCompatActivity() {

    @Inject
    lateinit var hiltRepo: HiltRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hilt_diactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tv: TextView = findViewById(R.id.activity_hilt_tv)

        tv.text = hiltRepo.sayHello()
    }
}