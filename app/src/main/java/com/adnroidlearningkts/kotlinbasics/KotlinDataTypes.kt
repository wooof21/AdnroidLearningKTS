package com.adnroidlearningkts.kotlinbasics


/**
 * Integer: Byte, Short, Int, Long
 *
 * Byte: -128 to 127
 * Short: 16 bit signed integers, -32768 to 32767
 * Int: 32 bit signed integers, approx. -2 Billion to 2 Billion
 * Long: 64 bit signed integers, -9223372036854775807 to 9223372036854775807
 */

var myByte : Byte = 100
var myShort: Short = -32768
var myInt: Int = 1000000
var myLong: Long = 9223372036854775807

/**
 * Floating point numbers: float, double
 * Float: 32 bit floating point numbers, which are used for single precision floating point calculation
 *  - has a suffix "f"
 * Double: 64 bit floating point numbers, which are used for double precision floating point calculation
 *  - default floating point literal, no suffix
 */

var myFloat: Float = 3.1415f
var myDouble: Double = 3.1415

//Boolean: true or false
var isCondition: Boolean = true

/**
 * Characters: a single character - Letter, Digit, Symbol or Special Character ...
 * - enclosed in single quote
 */

var myChar: Char = '$'

//String
var text1: String = "Hello"
var text2 = " World"
//String Concatenation
var text3 = text1 + text2

/**
 * String Templates: embed expressions and variables within the strings using String Templates
 */
var name = "Jack"

/**
 * In Kotlin, if you define two variables with the same name at the top level
 * (i.e., outside of any class or function) in different Kotlin files but in the same package,
 * the compiler will throw a "Conflicting declarations" error
 *
 * Reason: Top-level Declarations Are Visible in the Same Package
 *
 * Kotlin allows you to define functions and variables at the top level of a file (outside classes), but when you do that:
 *
 *     Those declarations are package-level, meaning they are essentially global within that package.
 *
 *     If two files in the same package declare the same name at the top level, Kotlin doesn’t know which one to use — hence the conflict.
 *
 * to Avoid:
 *
 *  1. Use unique names at the top level.
 *
 *  2. Encapsulate in objects or classes:
 *      object FileA {
 *          val message = "Hello from A"
 *      }
 *
 *      object FileB {
 *          val message = "Hello from B"
 *      }
 *
 *  to reference:
 *      println(FileA.message)
 *      println(FileB.message)
 *
 *  3. Use different packages for each file if the separation is logical.
 */
//var age = 30
var info = "Name: $name and Age: $age"

/**
 * String Interpolation: to evaluate expressions within String Template
 */
//var x, y: Int = 0 wont work
var x = 5; var y = 6
var sum = "The sum of $x and $y is ${x + y}"

/**
 * String Functions and Properties
 * ...
 * Comparison: .equals(), .compareTo()
 */
var info_length = info.length
var info_sub = info.substring(0, 7) // same as java, end index is excluded
fun main() {
    println(info)
    println(sum)
    println(info_length)
    println(info_sub)
}