package com.adnroidlearningkts.kotlinbasics.oop

/**
 * Syntax:
 *
 *  fun functionName(param: Type ...): ReturnType {
 *
 *  }
 *
 *  when there is no return value: ReturnType = Unit, like void in Java or without ReturnType
 *
 *  function parameter can have default value, allow you to call function without specifying all
 *  arguments
 */

fun main() {

    fun say(msg: String): Unit {
        println(msg)
    }

    say("Hello World")

    println("Sum: ${sum(3, 5)}")

    info("Jack")
    info("John", 30)

    println("Sum: ${sum(2.5, 5.5)}")
    println("Sum: ${sum(1, 2, 5)}")
}

fun sum(val1: Int, val2: Int): Int {
    return val1 + val2
}

fun info(name: String, age: Int = 10) {
    println("Name: $name and Age: $age")
}

/**
 * Function overloading: to define multiple functions with same function name in the
 * same scope but with diff. parameters
 */

fun sum(val1: Double, val2: Double): Double {
    return val1 + val2
}

fun sum(x: Int, y: Int, z: Int): Int {
    return x + y + z
}

