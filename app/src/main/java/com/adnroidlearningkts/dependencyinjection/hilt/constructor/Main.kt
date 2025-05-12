package com.adnroidlearningkts.dependencyinjection.hilt.constructor




fun main() {
    /**
     * Hilt/Dagger generate the Components class at compile-time
     *
     * Build project first to generate the DaggerCarComponent class
     */
    val carConstructorComponent: CarConstructorComponent = DaggerCarConstructorComponent.create()

    //retrieve the Car instance
    val car: CarConstructor = carConstructorComponent.getCar()

    car.drive()
}