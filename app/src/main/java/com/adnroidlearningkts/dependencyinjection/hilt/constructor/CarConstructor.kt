package com.adnroidlearningkts.dependencyinjection.hilt.constructor

import javax.inject.Inject

/**
 * Constructor Injection
 *
 * Car depend on Engine class, and Hilt handles injecting it
 */
class CarConstructor @Inject constructor(private val engine: EngineConstructor) {

    fun drive() = println(engine.start())
}