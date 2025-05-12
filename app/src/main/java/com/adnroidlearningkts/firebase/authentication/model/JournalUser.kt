package com.adnroidlearningkts.firebase.authentication.model

import android.app.Application

/**
 * This class is intended to hold application-level state, specifically the username and user ID of the current user.
 *
 * The class implements a singleton pattern for the JournalUser class.
 * The goal is to have a single, globally accessible instance of JournalUser throughout the application.
 * This is a common pattern for managing shared state or resources, such as user information in this case.
 *
 * Improvement:
 *  1. Avoid Inheriting from Application for a Singleton
 *      Inheriting directly from Application for a singleton like JournalUser is generally not recommended.
 *      The Application class has a specific lifecycle managed by the Android system.
 *      Creating own instance of JournalUser that inherits from Application bypasses this lifecycle management
 *      and can lead to unexpected behavior and resource leaks.
 *      The Application instance is a system-level singleton and should be accessed through context.applicationContext when needed.
 *
 *      * Use Kotlin's object Declaration for Singletons: Kotlin provides the object keyword specifically for declaring singletons.
 *      This is the most idiomatic and concise way to create a singleton in Kotlin.
 *      It handles thread-safe initialization by default.
 *      The current companion object implementation with the manual check for null is essentially
 *      trying to replicate the functionality of object, but it's more verbose and less safe in terms of
 *      thread safety compared to Kotlin's built-in mechanism.
 *
 * With the object declaration, Kotlin handles the singleton instance creation automatically.
 * No need the manual if(field == null) check and instance creation within the getter of instance.
 * This logic is redundant and less efficient than the object declaration.
 */
//class JournalUser: Application() {

object JournalUser {
    var username: String? = null

    /**
     * If userId absolutely must be set during the initialization of the
     * JournalUser singleton (which is less typical for a globally accessible user object),
     * might consider passing the user ID when first "initialize" the user in the application flow.
     * However, the object declaration itself doesn't take constructor parameters.
     * Would need to handle this by having methods on the object to set user details.
     */
    private var _userId: String? = null

    val userId: String?
        get() = _userId

    fun setUserDetails(name: String?, id: String?) {
        username = name
        _userId = id
    }

    fun clearUserDetails() {
        username = null
        _userId = null
    }

    fun isUserAvailable(): Boolean {
        return userId != null
    }

}