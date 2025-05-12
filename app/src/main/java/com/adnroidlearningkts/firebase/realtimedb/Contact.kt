package com.adnroidlearningkts.firebase.realtimedb

import com.google.firebase.database.IgnoreExtraProperties

/**
 * Firebase Realtime Database relies on Java/Kotlin bean conventions to serialize and deserialize data.
 * For a class to be successfully serialized and deserialized by Firebase, it generally needs:
 *
 *      1. A public no-argument constructor: Firebase uses this to create an instance of your class when reading data from the database.
 *      2. Public getters for the properties you want to store:
 *          Firebase uses these methods to get the values of your class's properties to write to the database.
 *          The getter method names should follow the standard Java bean naming convention
 *          (e.g., getName() for a property named name).
 *      3. Optional: Public setters for the properties you want to read:
 *          While not strictly required for serialization (writing data),
 *          they are necessary for deserialization (reading data from the database into an object).
 *          Setters should also follow the standard Java bean naming convention
 *          (e.g., setName(String name)).
 *
 *    With default values create a no-argument default constructor, which is needed
 *    for deserialization from a DataSnapshot.
 */
@IgnoreExtraProperties
data class Contact(val name: String? = null, val email: String? = null)
//data class Contact(val name: String = "", val email: String = "")
