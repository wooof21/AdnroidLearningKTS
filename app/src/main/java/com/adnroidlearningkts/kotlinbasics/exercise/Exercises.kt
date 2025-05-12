package com.adnroidlearningkts.kotlinbasics.exercise

import java.util.Scanner




fun main() {

//    countChars()

    reverse()
}


fun reverse() {
    //reverse a string
    var scanner = Scanner(System.`in`)
    println("Enter some text:")
    var text = scanner.nextLine()

    val chs = text.toCharArray()

    var reverse = ""

    for(i in text.length-1 downTo 0) {
        reverse += chs[i]
    }
    println(reverse)

    //another way
    var reverse2 = text.reversed()
    println(reverse2)
}

fun countChars() {

    /**
     * To count how many letter, digits, spaces, and others in a given string
     */
    var scanner = Scanner(System.`in`)
    println("Enter some text:")
    var text = scanner.nextLine()

    var letter = 0; var digit = 0; var space = 0; var other = 0

    //convert to character array
    var chs = text.toCharArray()

    for(ch in chs) {
        if(Character.isLetter(ch)) {
            letter++
        } else if(Character.isDigit(ch)) {
            digit++
        } else if(Character.isSpaceChar(ch)) {
            space++
        } else {
            other++
        }
    }
    println("Letter count: $letter - Space count: $space - Digit count: $digit - Others: $other")


    //arrays iterating with index
    for((index, ch) in chs.withIndex()) {
        println("Index: $index, Char: $ch")
    }
    println("-----------------------")
    for(c in chs.withIndex()) {
        println("Index: ${c.index}, Char: ${c.value}")
    }

//    for(i in chs.indices) {
//        println(chs[i])
//    }
}