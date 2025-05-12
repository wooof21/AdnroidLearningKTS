package com.adnroidlearningkts.adapters.listview.model

/**
 * data class: special class designed to primarily hold data
 *      - The Kotlin compiler automatically generates several useful methods,
 *          saving from writing boilerplate code.
 * equals() and hashCode(): For comparing instances of the data class based on their properties.
 *          Two data class instances are considered equal if
 *          all their corresponding properties have the same values.
 *
 * toString(): Returns a human-readable string representation of the object,
 *      including the class name and all property values.
 *
 * componentN() functions: For each property declared in the primary constructor,
 * a componentN() function is generated (e.g., component1(), component2(), etc.).
 * These functions allow you to easily access the values of the properties in the order
 * they are declared in the constructor. This is especially useful for destructuring declarations.
 *
 * copy(): Creates a new instance of the data class, copying all the properties from the original object.
 * You can optionally modify some properties during the copy. To define a data class,
 * you simply prefix the class declaration with the data keyword.
 * All properties that you want to be considered for equality, toString, copy,
 * and component functions must be declared in the primary constructor of the data class using val or var.
 * Properties declared inside the class body, but not in the primary constructor,
 * are not included in the automatically generated functions.
 */
data class Planet(
    val img: Int,
    val planetName: String,
    val moonCount: String
)
