package com.adnroidlearningkts.dependencyinjection.manualinjection.withdi

import com.adnroidlearningkts.dependencyinjection.manualinjection.withoutdi.Engine

fun main() {
    val engine = Engine()

    /**
     * Dependency is injected here: Constructor Injection
     */
    val car = Car(engine)

    car.drive()
}