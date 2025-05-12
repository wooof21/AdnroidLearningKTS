package com.adnroidlearningkts.kotlinbasics.oop

/**
 * Inheritance: base class
 *
 * open: is a keyword used to declare a class, function or property as open for extension
 * means it can be subclassed or inherited or overridden by other classes or functions
 *
 * () -> refer to the primary constructor
 */
open class Robot() {

    var name: String
    var modelyear: String

    /**
     * init block: used to executed code when an instance of class is created
     * often used to perform additional setup or initialization for properties in a class
     * - is executed after primary and any secondary constructor
     *
     * secondary constructor: initialize objects, provide diff. sets of parameters
     *
     * If the class has a primary constructor, each secondary constructor needs to
     * delegate to the primary constructor, either directly or indirectly
     * through another secondary constructor(s).
     * Delegation to another constructor of the same class is done using the this keyword:
     */

    init {
        name = ""
        modelyear = ""
        println("A new Robot is created in init block")
    }

    //secondary constructor
    constructor(name: String, modelYear: String): this() {
        this.name = name
        this.modelyear = modelyear
    }
    constructor(name: String) : this() {
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