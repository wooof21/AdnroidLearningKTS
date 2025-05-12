package com.adnroidlearningkts.dependencyinjection.hilt.constructor

import javax.inject.Inject


/**
 * Constructor Injection
 *
 * @Inject: tells Hilt how to create an instance of Engine
 *
 * constructor(): the primary constructor. Since there are no parameters within the parentheses,
 * this indicates that EngineHilt can be constructed without any arguments.
 *
 * When an instance of EngineHilt is requested by another component (like an Activity or Fragment)
 * that is also managed by Hilt, Hilt will create a new EngineHilt object using its default constructor (marked with @Inject).
 */
class EngineConstructor @Inject constructor() {

    fun start() = "Engine Started"
}