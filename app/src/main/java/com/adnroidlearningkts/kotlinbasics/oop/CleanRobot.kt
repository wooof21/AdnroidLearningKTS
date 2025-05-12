package com.adnroidlearningkts.kotlinbasics.oop


/**
 * Subclass, inherit from Robot(base class)
 */
class CleanRobot(name: String): Robot(name) {

    fun clean() {
        println("$name is cleaning the house")
    }
}