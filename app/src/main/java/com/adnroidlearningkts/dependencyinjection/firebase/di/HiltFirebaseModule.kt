package com.adnroidlearningkts.dependencyinjection.firebase.di

import com.adnroidlearningkts.dependencyinjection.firebase.repository.DataRepo
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Provide the Firestore & DataRepo dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object HiltFirebaseModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    /**
     * Since Firestore is not constructor-injectable, need the provideFirestore() fun to tell Hilt
     * how to get an instance of it
     *
     * And since DataRepo has a dependency of Firestore, even the DataRepo itself has the @Inject constructor,
     * because one of its dependencies (FirebaseFirestore) needs to be provided via a @Provides function,
     * also need a way to tell Hilt how to create a DataRepo using the provided FirebaseFirestore via
     * @Provides fun
     */
//    @Provides
//    @Singleton
//    fun provideDataRepo(firestore: FirebaseFirestore): DataRepo {
//        return DataRepo(firestore)
//    }

    /**
     * When lazy inject the firestore, change the parameter type in @Provides fun
     */
    @Provides
    @Singleton
    fun provideDataRepo(firestoreProvider: dagger.Lazy<FirebaseFirestore>): DataRepo {
        return DataRepo(firestoreProvider)
    }
}