package com.adnroidlearningkts.dependencyinjection.manualinjection.withdi

import com.adnroidlearningkts.dependencyinjection.manualinjection.withoutdi.Engine


/**
 * Inject Dependency(Engine) in constructor, the Dependency is passed from outside the class
 */
class Car(private val engine: Engine) {

    fun drive() = println(engine.start())
}