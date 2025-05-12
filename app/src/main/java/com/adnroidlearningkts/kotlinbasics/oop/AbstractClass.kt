package com.adnroidlearningkts.kotlinbasics.oop

/**
 * Abstract Class: cannot be instantiated by their own, and are typically meant to be subclassed
 *
 * Abstract Function: declare in the abstract class and do not have an implementation
 * in the abstract class but must be implemented in subclass
 *
 * e.g.
 * open class Robot() {}
 *
 * can be instantiated: var robot = Robot("Robot")
 *
 * but
 * abstract class Robot() {}
 *
 * cannot be instantiated: var robot = Robot("Robot") this way
 *
 * To implement RobotActions Interface
 */
abstract class AbstractRobot: RobotActions {
    /**
     * Getter and Setter are typically used when require custom operations
     */
    var name: String = ""
        get() {
            println("In Getter")
            return field
        }
        set(value) {
            println("In Setter")
            field = value
        }
    var modelyear: String = ""

    constructor(name: String, modelYear: String) {
        this.name = name
        this.modelyear = modelyear
    }
    constructor(name: String) {
        this.name = name
        this.modelyear = "Unknown"
    }

    abstract fun move()

}

/**
 * Interface can also implemented on subclass level
 *
 * class AnotherRobot: AbstractRobot, RobotActions {}
 */
class AnotherRobot: AbstractRobot {
    constructor(name: String, modelYear: String): super(name, modelYear)
    constructor(name: String): super(name)

    override fun move() {
        println("Override abstract function move(): move by wheels")
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

}


fun main() {

    //error
    // var robot = AbstractRobot("AbstractRobot")

    //Polymorphism
    val robot: AbstractRobot = AnotherRobot("AnotherRobot")
}
