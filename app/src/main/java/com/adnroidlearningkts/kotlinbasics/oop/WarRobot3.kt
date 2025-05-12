package com.adnroidlearningkts.kotlinbasics.oop

class WarRobot3: Robot3 {

    constructor(name: String, modelyear: String): super(name, modelyear)
    constructor(name: String): super(name)

    fun fire() {
        println("$name is firing missiles ...")
    }
}