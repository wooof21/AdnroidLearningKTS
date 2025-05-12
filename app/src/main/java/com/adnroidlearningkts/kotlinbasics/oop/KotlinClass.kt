package com.adnroidlearningkts.kotlinbasics.oop


fun main() {
    //creating Objects from Class
    val car1: Car = Car("X5")
    car1.startEngine()
    car1.drive("Forward")

    val car2 = Car("A7")
    car2.startEngine()
    car2.drive("North East")


    //inheritance
    val cleanRobot = CleanRobot("CleanRobot")
    cleanRobot.start()
    cleanRobot.speak("Hello")
    cleanRobot.clean()

    val warRobot2 = WarRobot2("WarRobot2")
    warRobot2.start()
    warRobot2.speak("Fire in the hole")
    warRobot2.fire()

    val warRobot = WarRobot("WarRobot", "2024")
    warRobot.start()
    warRobot.speak("Fire in the hole")
    warRobot.fire()

    val warRobot3 = WarRobot3("WarRobot3")
    warRobot3.start()
    warRobot3.speak("Fire in the hole")
    warRobot3.fire()

    val warRobot4 = WarRobot3("WarRobot3", "2000")
    warRobot4.start()
    warRobot4.speak("Fire in the hole")
    warRobot4.fire()
}
/**
 * Primary Constructor: define and initialize properties for a Class
 *
 * class ClassName(param1: Type ...){}
 */
class Car(private val model: String) {

    fun startEngine() {
        println("$model Engine started ...")
    }

    fun drive(direction: String) {
        println("$model moving $direction ...")
    }
}

