package com.adnroidlearningkts.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * BroadcastReceiver: an Android component that allows the registration and handling of system wide
 * broadcast messages
 *  - enables communication between different parts of an Android application or between different
 *      applications
 *  - listen for broadcast messages announced by Android system or other applications
 *  - system event: screen rotation changes, battery low events
 *  - custom broadcast events sent by other applications
 */
class AirplaneModeReceiver: BroadcastReceiver() {

    //called when receive a broadcast
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isAirplaneModeOn = intent.getBooleanExtra("state", false)
            val message = if (isAirplaneModeOn) {
                "Airplane mode is ON"
            } else {
                "Airplane mode is OFF"
            }
            context?.let {
                Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}