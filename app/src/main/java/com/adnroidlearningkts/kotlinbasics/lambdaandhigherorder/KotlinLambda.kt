package com.adnroidlearningkts.kotlinbasics.lambdaandhigherorder



fun main() {
    /**
     * Lambda Expression: concise way to define anonymous fun
     *
     * Often used for passing functions as arguments, defining small inline functions
     * and making code more readable and expressive
     * Can also be assigned to variables
     *
     * Commonly used with higher order functions like map, filter, forEach ...
     */

    // Type 1 - With Parameters and Return Value
    val add: (Int, Int) -> Int = {a, b -> a + b}
    val result = add(3, 5)

    // Type 2 - With Params & No Return Value
    val add2:(Int, Int) -> Unit = {a, b -> println(a+b) }
    add2(3, 5)

    // Type 3 - No Params But With Return Value
    val type3:() -> String = {"Some Strings ..."}
    println(type3.invoke())

    // Type 4 - No Params & NO Return Value
    val type4:() -> Unit = { println("No Params & No Return Value") }
    type4()

    //Direct use of Lambda Expression
    println({a:Int, b:Int -> a * b}(3, 5))

    val numbers = listOf(1, 2, 3, 4, 5)
    val sqrNumbers = numbers.map { num -> num * num }
    println(sqrNumbers)

}