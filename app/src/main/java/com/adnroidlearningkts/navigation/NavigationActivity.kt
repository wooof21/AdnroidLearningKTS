package com.adnroidlearningkts.navigation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R

/**
 * Jetpack Navigation: https://developer.android.com/guide/navigation
 *
 * Safe Args Gradle Plugin: https://developer.android.com/guide/navigation/use-graph/safe-args
 *
 *      * The recommended way to navigate between destinations is to use the Safe Args Gradle plugin.
 *      This plugin generates object and builder classes that enable type-safe navigation between destinations.
 *      Use Safe Args for navigating and passing data between destinations.
 *
 */
class NavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_navigation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}