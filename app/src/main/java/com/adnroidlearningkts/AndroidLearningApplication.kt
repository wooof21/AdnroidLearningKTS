package com.adnroidlearningkts

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Entry point for Hilt's DI system
 *
 * @HiltAndroidApp: it tells Hilt to generate a DI container at app level
 *  * it ensures that Hilt is initialized before any part of the app is created
 *  * ensures all dependencies are ready to use when app started
 */
@HiltAndroidApp
class AndroidLearningApplication: Application()