package com.adnroidlearningkts.kotlinbasics


/**
 *  - Variable Declaration: var or val
 *      . var: declare a mutable variable, one that can change its value
 *      . val: declare an immutable variable, ont that cant change its value
 *  - Type Inference: Kotlin can determine the variable type based on the init value,
 *      no need to specify the type explicitly
 *  - Assign Initial Value(optional)
 */

var age: Int = 30 // var age = 30

val PI: Double = 3.1415926

fun main() {
    println("Age: $age")
}

