package com.adnroidlearningkts.dependencyinjection.firebase.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Provider

//Inject the firestore instance
//class DataRepo @Inject constructor(private val firestore: FirebaseFirestore) {

class DataRepo @Inject constructor(private val firestoreProvider: dagger.Lazy<FirebaseFirestore>) {

    //lazy inject
    private val firestore: FirebaseFirestore by lazy {
        firestoreProvider.get()
    }

    /**
     * Fetch data from a specific Firestore collection and return it as a LiveData object
     *
     * MutableLiveData: allow update to its value
     */
    fun fetchData(collection: String): LiveData<List<String>> {
        val liveData = MutableLiveData<List<String>>()

        firestore.collection(collection).get()
            .addOnSuccessListener {
                val data = it.map {
                    it.getString("message") ?: ""
                }
                liveData.value = data
            }.addOnFailureListener {
                liveData.value = listOf("Error: ${it.message}")
            }

        return liveData
    }
}