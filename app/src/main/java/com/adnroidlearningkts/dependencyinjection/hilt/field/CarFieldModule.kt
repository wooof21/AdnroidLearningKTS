package com.adnroidlearningkts.dependencyinjection.hilt.field

import com.adnroidlearningkts.dependencyinjection.hilt.constructor.EngineConstructor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CarFieldModule {

    @Provides
    @Singleton
    fun provideEngine() = EngineConstructor()

    /**
     * No need to provideCar, since Car use Field Injection
     */
}