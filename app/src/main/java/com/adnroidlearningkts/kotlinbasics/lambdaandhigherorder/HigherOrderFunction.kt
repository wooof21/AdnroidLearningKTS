package com.adnroidlearningkts.kotlinbasics.lambdaandhigherorder

/**
 * Higher Order Function: accept one or more functions as parameters and can return
 * a function as result
 */

fun main() {

    /**
     * When you have Lambda Expression passed as the last parameter, can write this way
     * operateOnNumbers(3, 5){x, y -> x+y} or
     * operateOnNumbers(3, 5, {x, y -> x+y})
     *
     * Summary: essential feature of functional programming that allows to work with functions
     * as data, enabling you to write more modular and flexible code by passing and returning
     * functions as value
     */
    val addResult = operateOnNumbers(3, 5){ x, y -> x + y }
    val multiplyRes = operateOnNumbers(3, 5){x, y -> x*y}

    println("Add: $addResult - Multiply: $multiplyRes")
}

/**
 * operateOnNumbers - higher order function
 *
 * operation(Lambda Expression) - function passed as parameter to higher order function
 */
fun operateOnNumbers(a:Int, b:Int, operation:(Int, Int) -> Int):Int{
    return operation(a, b)
}