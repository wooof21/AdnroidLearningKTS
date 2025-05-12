package com.adnroidlearningkts.kotlinbasics.datastructures


fun main() {

    /**
     * List: Ordered collection in which the elements can be accessed by indices
     *
     * ImmutableList: read-only list
     */

    //Immutable
    val fruits = listOf("Apple", "Orange", "Banana")
    //error
    //fruits.add("Melon")
    //Access element
    println("0: ${fruits[0]}")
    println("1: ${fruits.get(1)}")
    for(fruit in fruits) {
        println(fruit)
    }

    //Mutable List
    val veges = mutableListOf("Potato", "Tomato", "Broccoli")
    val colors = arrayListOf("Green", "Black", "Red", "Blue", "White")
    //Add element to list
    veges.add("Carrots")
    colors.add("Grey")
    //Remove element
    veges.removeAt(0)
    colors.removeAt(0)
//    veges.removeAll { vege -> vege.length > 6 }

    //update element
    veges[0] = "Garlic"
    colors[1] = "Yellow"
    println(veges)

}