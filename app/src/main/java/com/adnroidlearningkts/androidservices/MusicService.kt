package com.adnroidlearningkts.androidservices

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

/**
 * Service: a component that runs in background and does not have a user interface
 *
 *  Need to register Service in AndroidManifest.xml
 */
class MusicService: Service() {

    //MediaPlayer: to play music
    private lateinit var mediaPlayer: MediaPlayer

    /**
     * onBind: used when the Service allows other components to bind to it
     *  - when a component binds to the Service, it establishes a connection that allows the
     *      component to interact with Service, invoke method, and receive data
     *
     * return null: since not going to bind to any components
     */
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    /**
     * called when a client, such as an Activity or another Service, starts this Service
     * using the startService method
     *
     * `intent`: carry information that might be passed to the Service when it starts
     *  - can include data or instructions provided by the component that started the Service
     *
     * `flags`: provide additional info about how the Service was started
     *
     * `startId`: identifier for the specific instance of the Service
     *  - each time the Service is started, the startID is incremented
     *  - can be used to identify and manage the different start requests
     *
     *  Typically include the logic for handling the start command, includes:
     *      - initializing resources
     *      - performing background task
     *      - responding to instructions provided though the intent
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)

        //play the sound on loop
        mediaPlayer.isLooping

        //start the media player
        mediaPlayer.start()

        /**
         * Return value: returns an integer that represents the Service's behavior regarding restarts
         *
         * common return values:
         *
         * `START_STICKY`: indicate the service should be restarted if its terminated by the system
         *         after it has been started
         *         the system will recreate the service with a null intent, and
         *         need to handle reinitialization of any necessary resources
         *   Use this return type if a service performs periodic tasks such as:
         *   background data synchronization, where you want to ensure that the service
         *   resumes operation after being temporary killed
         *
         *   `START_NOT_STICKY`: indicate the service should not be restarted if its terminated by the system
         *                  after it has been terminated
         *                  The service will remain stopped unless explicitly started again by the application
         *                  or component
         *    Suitable for services that perform specific tasks and dont need to be running continuously
         *    such as:
         *    service that plays a sound effect when a notification is received
         *
         *    `START_REDELIVER_INTENT`: similar to `START_STICKY`, but it also re-delivers the last
         *                              intent if the Service is terminated
         */

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}