package com.adnroidlearningkts.dependencyinjection.hilt.method

import com.adnroidlearningkts.dependencyinjection.hilt.constructor.CarConstructorModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CarMethodModule::class])
interface CarMethodComponent {

    fun inject(car: CarMethod)
}