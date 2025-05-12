package com.adnroidlearningkts.kotlinbasics


fun main() {
    //Declaration
    var osNames = arrayOf("Windows", "Android", "IOS", "Linux")
    //access elements
    println("Array1 ${osNames.contentToString()}")
    println("First Elements: ${osNames[0]}")
    //modify
    osNames[2] = "MacOS"
    println("3rd Elements: ${osNames[2]}")
    println("Array2 ${osNames.contentToString()}")

    println("Array size: ${osNames.size}")

    //iterating array
    for(name in osNames) {
        println(name)
    }

    osNames.forEach { name -> println(name) }
}