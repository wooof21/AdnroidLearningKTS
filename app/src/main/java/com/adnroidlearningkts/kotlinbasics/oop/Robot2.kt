package com.adnroidlearningkts.kotlinbasics.oop

open class Robot2(val name: String) {

    var modelyear: String
    /**
     * If the class has a primary constructor, each secondary constructor needs to
     * delegate to the primary constructor, either directly or indirectly
     * through another secondary constructor(s).
     * Delegation to another constructor of the same class is done using the this keyword:
     */


    init {
        modelyear = ""
        println("A new Robot is created in init block")
    }

    //secondary constructor
    constructor(name: String, modelYear: String): this(name) {
        this.modelyear = modelyear
    }


    fun start() {
        println("Robot is starting ...")
    }

    fun speak(msg: String) {
        println("$name says $msg")
    }
}