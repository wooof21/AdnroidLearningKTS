package com.adnroidlearningkts.kotlinbasics

/**
 * if, if-else - same as Java
 *
 * when: similar to switch, allow you to evaluate a value against multiple possible
 * cases and execute the code block corresponding to the first matching case
 */
//when expression
fun main() {
    var day = 3
    when(day) {
        1 -> println("monday")
        2 -> println("Tues")
        3 -> println("Weds")
        else -> println("Unknown Day")
    }
}