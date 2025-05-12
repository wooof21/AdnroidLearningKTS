package com.adnroidlearningkts.dependencyinjection

import javax.inject.Inject

//mock repo to provide the data
class HiltRepo @Inject constructor(){

    fun sayHello() = "Hello From Hilt"
}