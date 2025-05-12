package com.adnroidlearningkts.dependencyinjection.hilt.field

import javax.inject.Inject

class CarField {

    /**
     * Field Injection: EngineField instance will be injected into `engine` field
     *      * filed cannot be private
     */
    @Inject
    lateinit var engine: EngineField

    fun drive() = println(engine.start())
}