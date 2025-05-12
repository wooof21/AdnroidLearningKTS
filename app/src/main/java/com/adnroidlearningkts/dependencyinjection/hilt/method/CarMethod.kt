package com.adnroidlearningkts.dependencyinjection.hilt.method

import javax.inject.Inject

class CarMethod {

    lateinit var engine: EngineMethod

    /**
     * Method Injection: Hilt will inject the EngineMethod instance into this method
     */
    @Inject
    fun installEngine(engine: EngineMethod) {
        this.engine = engine
    }

    fun drive() = println(engine.start())
}