package com.adnroidlearningkts.dependencyinjection.hilt.constructor

import com.adnroidlearningkts.dependencyinjection.hilt.field.CarField
import dagger.Component
import javax.inject.Singleton

/**
 * CarComponent: Since working in an non Android environment, need to make a bridge between
 * the CarModule and other classes.
 *
 * This interface is not required when working in an Android project
 *
 * @Component(modules = [CarModule::class]): define an Dagger component,
 *  * A Component is like a bridge between modules(which provides the dependencies) and classes that
 *      need those dependencies
 *  * modules: defines the modules that the Component should use to resolve dependencies
 *
 * The interface defines the API for retrieving the dependencies from Dagger Dependency Graph
 */
@Singleton
@Component(modules = [CarConstructorModule::class])
interface CarConstructorComponent {

    fun getCar(): CarConstructor

}