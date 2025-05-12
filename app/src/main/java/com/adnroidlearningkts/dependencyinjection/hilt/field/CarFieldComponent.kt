package com.adnroidlearningkts.dependencyinjection.hilt.field

import com.adnroidlearningkts.dependencyinjection.hilt.constructor.CarConstructorModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CarFieldModule::class])
interface CarFieldComponent {

    /**
     * add fun to allow Field Injection,
     * This fun tells Dagger to inject dependencies into the fields of CarField class
     */
    fun inject(car: CarField)
}