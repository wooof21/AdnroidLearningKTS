package com.adnroidlearningkts.kotlinbasics

fun main() {

    //for loop, range is inclusive
    for(i in 1..5) {
        println(i)
    }

    //while loop
    var count = 0
    while(count < 5) {
        println("Count: $count")
        count++
    }

    //do-while loop
    var x = 1
    do {
        println("This line is executed at least once")
        x++
    } while (x < 0)

    /**
     * Break: terminate the loop and transfer the control to the statement following the loop
     * Continue: skip the current iteration and proceeds to the next iteration of loop
     */
    for(k in 1..10) {
        if(k == 5) break
        println("K: $k")
    }

    for(m in 1..10) {
        if(m % 2 == 0) continue
        println("M: $m")
    }
}
