package com.adnroidlearningkts.kotlinbasics.datastructures


fun main() {
    /**
     * Set: a collection of unordered unique elements - No Duplicate elements
     */

    //Immutable Set
    val veges = setOf("Potato", "Tomato", "Broccoli", "Broccoli")
    println(veges)

    //Mutable Set
    val colors = mutableSetOf("Red", "Green", "Black", "White", "Red")

    //Add
    colors.add("Grey")
    println(colors.add("Red"))

    //remove
    colors.remove("Red")

    //update
    colors.remove("Green")
    colors.add("Yellow")
    println(colors)



    for (vege in veges) {
        println(vege)
    }
}