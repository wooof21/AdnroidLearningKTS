package com.adnroidlearningkts.kotlinbasics.lambdaandhigherorder


/**
 * Main difference to Lambda Expression:
 * Anonymous Function specify the return type explicitly and use `fun` keyword
 * Lambda Expression often infer the return type from context
 *
 * Syntax:
 *
 * val anonymousFunction = fun(params ...): ReturnType {
 *      Function Body
 *      Return statement if needed
 * }
 */

fun main() {

    val numbers = listOf(1, 2, 3, 4, 5)

    //Anonymous Function to square the number
    val sqr = fun(num:Int): Int{
        return num * num
    }
    //use List.map to invoke the Anonymous Function
    val sqrNumbers = numbers.map(sqr)
    println(sqrNumbers)

    // Type 1 - With Parameters & Return Value
    val multiply = fun(a:Int, b:Int):Int {return a * b}
    println(multiply.invoke(3, 5))

    // Type 2 - With Params but No Return Value
    val multiply2 = fun(a:Int, b:Int):Unit {
        println(a * b)
    }
    multiply2.invoke(8, 8)

    // Type 3 - No Params but With Return Value
    val msg = fun(): String{ return "Some Strings ..."}
    println(msg())

    // Type 4 - No Params & No Return Value
    val msg2 = fun(){
        println("No Params & No Return Value")
    }
    msg2()
}