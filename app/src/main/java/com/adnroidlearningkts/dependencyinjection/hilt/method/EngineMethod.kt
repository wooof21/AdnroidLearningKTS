package com.adnroidlearningkts.dependencyinjection.hilt.method

import javax.inject.Inject

class EngineMethod @Inject constructor() {

    fun start() = "Engine Started"
}