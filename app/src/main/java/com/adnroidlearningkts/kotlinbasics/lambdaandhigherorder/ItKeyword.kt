package com.adnroidlearningkts.kotlinbasics.lambdaandhigherorder




/**
 * `it` keyword: implicit name for a single parameter in Lambda Expression or
 * Anonymous Function when that lambda or fun takes only one parameter
 *
 * a shorthand notation to simplify the code
 */
fun main() {

    val numbers = listOf(1, 2, 3, 4, 5)

    //Using Lambda Expression to square every number in a list
    val sqrNumbers = numbers.map { num -> num * num }
    println(sqrNumbers)

    //Using Anonymous Function to square the number
    val sqr = fun(num:Int): Int{
        return num * num
    }
    //Using the same Higher Order Function -map- and passing the anonymous function
    println(numbers.map(sqr))

    //Using `it` keyword
    val sqrNumbers2 = numbers.map{it * it}
    println(sqrNumbers2)

    //Getting even number from list
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println(evenNumbers)
}