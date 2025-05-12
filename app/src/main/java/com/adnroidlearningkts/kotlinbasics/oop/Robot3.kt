package com.adnroidlearningkts.kotlinbasics.oop

/**
 * () -> refer to the primary constructor
 *
 * In Kotlin, can also define a class without primary constructor, only secondary constructor
 * remove this() -> since the secondary constructors are no more relying or delegating on the
 * primary constructor
 */
open class Robot3 {

    var name: String
    var modelyear: String

    init {
        name = ""
        modelyear = ""
        println("A new Robot is created in init block")
    }

    //secondary constructor
    constructor(name: String, modelYear: String) {
        this.name = name
        this.modelyear = modelyear
    }
    constructor(name: String) {
        this.name = name
        this.modelyear = "Unknown"
    }

    fun start() {
        println("Robot is starting ...")
    }

    fun speak(msg: String) {
        println("$name says $msg")
    }
}