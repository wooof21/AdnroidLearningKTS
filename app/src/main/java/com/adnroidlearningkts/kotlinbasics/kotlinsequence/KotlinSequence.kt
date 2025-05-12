package com.adnroidlearningkts.kotlinbasics.kotlinsequence

import java.util.stream.Collectors


/**
 * Kotlin do not have a `Stream` API that's exactly the same as Java's,
 * it provides similar functionality with `sequences` and extension functions on collections.
 *
 * to convert the collection to sequence -> list.asSequence()
 *
 * Sequences are lazily evaluated, meaning that operations on them are not performed until a
 * terminal operation is called (e.g., toList(), count(), find()).
 * This can improve performance, especially for large collections or complex operations.
 *
 * Kotlin also provides a rich set of extension functions for collections (e.g., map, filter, reduce)
 * that can often achieve the same results as Java streams in a more concise and Kotlin-friendly way.
 *
 * Differences with `Stream()`
 *   - Lazy vs. Eager Evaluation: Java streams can be either lazy or eager,
 *      depending on the operations and whether they are parallel. Kotlin sequences are always lazy.
 *   - Nullability: Kotlin's null safety features work better with sequences.
 *   - API Design: Kotlin's collection extension functions often provide a more concise and
 *      expressive way to perform common operations compared to Java streams.
 *
 *  Kotlin can use Java Stream API - can use Java streams when needed, especially when working with Java libraries that use them.
 */

fun main() {

    val numbers = listOf(1, 2, 3, 4, 5)

    // Using Kotlin sequence
    val squaredEvensSequence = numbers.asSequence()
        .filter { it % 2 == 0 }
        .map { it * it }
        .toList() // Terminal operation to get the result

    // Using Kotlin collection functions
    val squaredEvensList = numbers
        .filter { it % 2 == 0 }
        .map { it * it }

    // Using Java Stream in Kotlin
    val evenSquares: List<Int> = numbers.stream()
        .filter { it % 2 == 0 }  // Filter even numbers
        .map { it * it }          // Square them
        .collect(Collectors.toList()) // Collect to a List

    println(squaredEvensSequence) // Output: [4, 16]
    println(squaredEvensList)    // Output: [4, 16]

    println("using Java Stream: $evenSquares") // Output: [4, 16]
}