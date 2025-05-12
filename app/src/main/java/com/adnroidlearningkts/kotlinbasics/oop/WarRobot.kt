package com.adnroidlearningkts.kotlinbasics.oop

class WarRobot: Robot {

    /**
     * Super: used to call the constructor of the super class - the parent class
     *
     * often used when a subclass wants to inherit and reuse the initialization logic of
     * the super class
     */

    constructor(name: String, modelyear: String): super(name, modelyear)
    constructor(name: String): super(name)

    fun fire() {
        println("$name is firing missiles ...")
    }
}