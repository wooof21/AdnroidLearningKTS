package com.adnroidlearningkts.dependencyinjection.hilt.field

import javax.inject.Inject

class EngineField @Inject constructor() {

    fun start() = "Engine Started"
}