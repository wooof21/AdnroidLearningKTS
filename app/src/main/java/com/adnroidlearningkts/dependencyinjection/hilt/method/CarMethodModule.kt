package com.adnroidlearningkts.dependencyinjection.hilt.method

import com.adnroidlearningkts.dependencyinjection.hilt.constructor.EngineConstructor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CarMethodModule {

    @Provides
    @Singleton
    fun provideEngine() = EngineConstructor()
}