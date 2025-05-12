package com.adnroidlearningkts.broadcastreceiver

import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R

class AirplaneModeActivity : AppCompatActivity() {

    private lateinit var airplaneModeReceiver: AirplaneModeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aireplane_mode)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /**
         * To register the BroadcastReceiver, either statically in the AndroidManifest.xml file
         * or dynamically in the code
         *
         * Static registration: suitable for Broadcast that are intended to be received even when
         * the app is not running
         *
         * Dynamic registration: allows more flexibility, such as registering and unregistering the
         * receiver at runtime
         */

        /**
         * IntentFilter: is a set of criteria such as action, category, and data that specifies
         *  the types of BroadcastReceiver should listen to
         *  - a BroadcastReceiver is associated with one or more IntentFilters
         *  - when IntentFilter matches, onReceive method in BroadcastReceiver is called
         */
        val intentFilter = IntentFilter("android.intent.action.AIRPLANE_MODE")

        airplaneModeReceiver = AirplaneModeReceiver()
        //dynamically register the BroadcastReceiver
        registerReceiver(airplaneModeReceiver, intentFilter)
    }

    //unregister the BroadcastReceiver to avoid memory leak
    override fun onDestroy() {
        unregisterReceiver(airplaneModeReceiver)
        super.onDestroy()
    }
}