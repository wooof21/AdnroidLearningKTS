package com.adnroidlearningkts.dependencyinjection.hilt.method



fun main() {

    val carComponent: CarMethodComponent = DaggerCarMethodComponent.create()

    val car = CarMethod()

    carComponent.inject(car)

    car.drive()
}