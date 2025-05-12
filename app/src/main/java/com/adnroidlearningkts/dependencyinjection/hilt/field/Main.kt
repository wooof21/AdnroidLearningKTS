package com.adnroidlearningkts.dependencyinjection.hilt.field


fun main() {
    val carComponent: CarFieldComponent = DaggerCarFieldComponent.create()

    val car = CarField()
    //inject dependencies into car fields
    carComponent.inject(car)

    car.drive()
}