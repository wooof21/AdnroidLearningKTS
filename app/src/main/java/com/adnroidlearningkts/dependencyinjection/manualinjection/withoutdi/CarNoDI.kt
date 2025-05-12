package com.adnroidlearningkts.dependencyinjection.manualinjection.withoutdi

class CarNoDI {

    /**
     * Without DI
     *
     * The class(Car) create the Dependency(Engine)
     */
    private val engine = Engine()

    fun drive() = println(engine.start())
}