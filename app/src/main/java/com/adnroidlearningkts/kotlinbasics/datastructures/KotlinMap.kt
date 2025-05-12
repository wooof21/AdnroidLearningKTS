package com.adnroidlearningkts.kotlinbasics.datastructures

/**
 * Map: an object that maps keys to values
 *
 * Keys: unique
 * Values: can be duplicates
 */

fun main() {

    //Immutable: keys to values
    val fruits = mapOf("Apple" to 5, "Orange" to 8, "Cherry" to 6)

    //access
    println(fruits.get("Apple"))
    println(fruits["Orange"])

    //Mutable
    val vegePrices = mutableMapOf("potato" to 5.5, "broccoli" to 8, "tomato" to 3.3)

    //update
    vegePrices["potato"] = 5

    //add
    vegePrices.put("garlic", 6)

    //iterating
    for((key, value) in vegePrices) {
        println("$key cost $value")
    }
}