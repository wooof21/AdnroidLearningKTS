package com.adnroidlearningkts.kotlinbasics

/**
 * Range: interval between 2 values
 *
 * 1. Closed Range: defined by .., includes both start and end index
 *      - start..end both inclusive
 *
 * 2. Half-Open Range: defined using `until` function or `..` in combination with the exclude end function
 *      - include start value but excluded end value
 *
 */

fun main() {
    //closed range
    var closedRange = 1..5
    //reverse order
    var range = 5 downTo 1

    //half-open
    var halfOpenRange = 1 until 5 //include 1, exclude 5
    var halfOpenRange2 = 1..<5 //include 1, exclude 5
}